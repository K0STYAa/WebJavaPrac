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
public class CustomerController {
    static CustomerDAOimpl customer_dao = new CustomerDAOimpl();

    @GetMapping("/customers/{customerId}")
    public String customer(@PathVariable String customerId, Model model) {
        ArrayList<PaidTicket> arr = customer_dao.getAllCustFlights(Long.parseLong(customerId));
        model.addAttribute("arr", arr);
        model.addAttribute("CustomerId", customerId);
        Customer customer = customer_dao.getCustomerById(Long.parseLong(customerId));
        model.addAttribute("CustomerName", customer.getCustomerFullName());
        model.addAttribute("CustomerAddress", customer.getCustomerAddress());
        model.addAttribute("CustomerPhone", customer.getCustomerPhoneNumber());
        model.addAttribute("CustomerEmail", customer.getCustomerEmail());
        return "customer";
    }

    @PostMapping("/customers/{customerId}")
    public String updateCustomer(@Valid Customer customer,
                                   BindingResult bindingResult,
                                   Model model,
                                   @PathVariable String customerId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/customers/" + customerId);
            return "customer_form";
        }
        customer_dao.updateCustomer(Long.parseLong(customerId), customer);
        return "redirect:/customers/{customerId}";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customer_dao.getAllCustomers());
        return "customer_list";
    }

    @PostMapping("/customers")
    public String addCustomer(@Valid Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            return "customer_form";
        }
        customer_dao.addCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/form")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/customers");
        return "customer_form";
    }

    @PostMapping("/customers/delete")
    public String deleteCustomer(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        customer_dao.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customerId}/update")
    public String updateCustomerForm(@PathVariable String customerId, Model model) {
        model.addAttribute("customer", customer_dao.getCustomerById(Long.parseLong(customerId)));
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/customers/" + customerId);
        return "customer_form";
    }
}