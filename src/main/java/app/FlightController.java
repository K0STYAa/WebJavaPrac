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
public class FlightController {
    static FlightDAOimpl flight_dao = new FlightDAOimpl();

    @GetMapping("/flights/{flightNumber}")
    public String flight(@PathVariable String flightNumber, Model model) {
        model.addAttribute("FlightId", flightNumber);
        Flight flight = flight_dao.getFlightById(Long.parseLong(flightNumber));
        model.addAttribute("FlightAirlineId", flight.getAirlineId());
        model.addAttribute("FlightDeparture", flight.getDeparture());
        model.addAttribute("FlightArrival", flight.getArrival());
        model.addAttribute("FlightDepartureTime", flight.getDepartureTime());
        model.addAttribute("FlightArrivalTime", flight.getArrivalTime());
        model.addAttribute("FlightCost", flight.getcost());
        model.addAttribute("FlightNumber", flight.getnumber());
        model.addAttribute("FlightMiles", flight.getmiles());
        return "flight";
    }

    @PostMapping("/flights/{flightNumber}")
    public String updateFlight(@Valid Flight flight,
                                   BindingResult bindingResult,
                                   Model model,
                                   @PathVariable String flightNumber) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/flights/" + flightNumber);
            return "flight_form";
        }
        flight_dao.updateFlight(Long.parseLong(flightNumber), flight);
        return "redirect:/flights/{flightNumber}";
    }

    @GetMapping("/flights")
    public String flights(Model model) {
        model.addAttribute("flights", flight_dao.getAllFlights());
        return "flight_list";
    }

    @PostMapping("/flights")
    public String addFlight(@Valid Flight flight, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            return "flight_form";
        }
        flight_dao.addFlight(flight);
        return "redirect:/flights";
    }

    @PostMapping("/flights/delete")
    public String deleteFlight(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        flight_dao.deleteFlight(id);
        return "redirect:/flights";
    }

    @GetMapping("/flights/form")
    public String accountForm(Model model) {
        model.addAttribute("flight", new Flight());
        model.addAttribute("allairports", new AirportDAOimpl().getAllAirports());
        model.addAttribute("allairlines", new AirlineDAOimpl().getAllAirlines());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/flights");
        return "flight_form";
    }

    @GetMapping("/flights/{flightNumber}/update")
    public String updateFlightForm(@PathVariable String flightNumber, Model model) {
        model.addAttribute("flight", flight_dao.getFlightById(Long.parseLong(flightNumber)));
        model.addAttribute("allairports", new AirportDAOimpl().getAllAirports());
        model.addAttribute("allairlines", new AirlineDAOimpl().getAllAirlines());
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/flights/" + flightNumber);
        return "flight_form"; 
    }
}