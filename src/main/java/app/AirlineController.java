package app;

import DAO.impl.*;
import model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class AirlineController {
    static AirlineDAOimpl airline_dao = new AirlineDAOimpl();

    @GetMapping("/airlines/{airlineId}")
    public String airline(@PathVariable String airlineId, Model model) {
        ArrayList<Flight> arr = airline_dao.getAllAirlineFlights(Long.parseLong(airlineId));
        model.addAttribute("arr", arr);
        ArrayList<CustAirRelation> arr2 = airline_dao.getAllAirlineCust(Long.parseLong(airlineId));
        model.addAttribute("arr2", arr2);
        model.addAttribute("AirlineId", airlineId);
        Airline airline = airline_dao.getAirlineById(Long.parseLong(airlineId));
        model.addAttribute("AirlineName", airline.getAirlineName());
        return "airline";
    }

    @PostMapping("/airlines/{airlineId}")
    public String updateAirline(@Valid Airline airline,
                                   BindingResult bindingResult,
                                   Model model,
                                   @PathVariable String airlineId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/airlines/" + airlineId);
            return "airline_form";
        }
        airline_dao.updateAirline(Long.parseLong(airlineId), airline);
        return "redirect:/airlines/{airlineId}";
    }

    @GetMapping("/airlines")
    public String airlines(Model model) {
        model.addAttribute("airlines", airline_dao.getAllAirlines());
        return "airline_list";
    }

    @PostMapping("/airlines")
    public String addAirline(@Valid Airline airline, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            return "airline_form";
        }
        airline_dao.addAirline(airline);
        return "redirect:/airlines";
    }

    @GetMapping("/airlines/form")
    public String addAirlineForm(Model model) {
        model.addAttribute("airline", new Airline());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/airlines");
        return "airline_form";
    }

    @PostMapping("/airlines/delete")
    public String deleteAirline(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        airline_dao.deleteAirline(id);
        return "redirect:/airlines";
    }

    @GetMapping("/airlines/{airlineId}/update")
    public String updateAirlineForm(@PathVariable String airlineId, Model model) {
        model.addAttribute("airline", airline_dao.getAirlineById(Long.parseLong(airlineId)));
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/airlines/" + airlineId);
        return "airline_form";
    }
}