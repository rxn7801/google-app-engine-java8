package com.sample.insurance.resource;

import com.sample.insurance.model.Customer;
import com.sample.insurance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class CustomerResource {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/")
    public String saveCustomer(Customer customer, BindingResult bindingResult, Model model) {
        repository.save(customer);
        model.addAttribute("customers", repository.findAll());
        return "index";
    }

    @GetMapping("/")
    public String getCustomers(Model model) {
        model.addAttribute("customers", repository.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addCustomer(Customer customer) {
        return "add";
    }

}
