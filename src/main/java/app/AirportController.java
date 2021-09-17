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
public class AirportController {
    static AirportDAOimpl airport_dao = new AirportDAOimpl();

    @GetMapping("/airports/{airportId}")
    public String airport(@PathVariable String airportId, Model model) {
        ArrayList<Flight> arr1 = airport_dao.getAllAirportDepFlights(Long.parseLong(airportId));
        model.addAttribute("arr1", arr1);
        ArrayList<Flight> arr2 = airport_dao.getAllAirportArrFlights(Long.parseLong(airportId));
        model.addAttribute("arr2", arr2);
        model.addAttribute("AirportId", airportId);
        Airport airport = airport_dao.getAirportById(Long.parseLong(airportId));
        model.addAttribute("AirportName", airport.getAirportName());
        return "airport";
    }

    @PostMapping("/airports/{airportId}")
    public String updateAirport(@Valid Airport airport,
                                   BindingResult bindingResult,
                                   Model model,
                                   @PathVariable String airportId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/airports/" + airportId);
            return "airport_form";
        }
        airport_dao.updateAirport(Long.parseLong(airportId), airport);
        return "redirect:/airports/{airportId}";
    }

    @GetMapping("/airports")
    public String airports(Model model) {
        model.addAttribute("airports", airport_dao.getAllAirports());
        return "airport_list";
    }

    @PostMapping("/airports")
    public String addAirport(@Valid Airport airport, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            return "airport_form";
        }
        airport_dao.addAirport(airport);
        return "redirect:/airports";
    }

    @GetMapping("/airports/form")
    public String addAirportForm(Model model) {
        model.addAttribute("airport", new Airport());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/airports");
        return "airport_form";
    }

    @PostMapping("/airports/delete")
    public String deleteAirport(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        airport_dao.deleteAirport(id);
        return "redirect:/airports";
    }

    @GetMapping("/airports/{airportId}/update")
    public String updateAirportForm(@PathVariable String airportId, Model model) {
        model.addAttribute("airport", airport_dao.getAirportById(Long.parseLong(airportId)));
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/airports/" + airportId);
        return "airport_form";
    }
}