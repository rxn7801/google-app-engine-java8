package com.sample.java8.resource;

import com.sample.java8.model.Customer;
import com.sample.java8.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

@Controller
public class CustomerResource {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/customer")
    public String saveCustomer(Customer customer, BindingResult bindingResult, Model model) {
        repository.save(customer);
        model.addAttribute("customers", repository.findAll());
        return "customer";
    }

    @GetMapping("/customer")
    public String getCustomers(Model model) {
        model.addAttribute("customers", repository.findAll());
        return "customer";
    }

    @GetMapping("/add")
    public String addCustomer(Customer customer) {
        return "add";
    }

    @PostMapping("/{id}")
    public String deleteCustomer(@PathVariable("id") Long id, Model model) {
        repository.deleteById(id);
        model.addAttribute("customers", repository.findAll());
        return "customer";
    }

    @GetMapping("/")
    public String searchCustomer(Customer search, BindingResult bindingResult, Model model) {

        Customer customerFound = repository.searchCustomer(search.getLastName(),
                search.getPolicyNumber(), search.getDateOfBirth(), search.getPostCode());

        if(customerFound == null ){
            model.addAttribute("customers", emptyList());
        } else
            model.addAttribute("customers", singletonList(customerFound));

        return "index";


    }

    @PostMapping("/")
    public String fetchCustomerResult(Customer customer, BindingResult bindingResult, Model model) {
        Customer customerFound = repository.searchCustomer(customer.getLastName(),
                customer.getPolicyNumber(), customer.getDateOfBirth(), customer.getPostCode());

        if(customerFound == null ){
            model.addAttribute("customers", null);
        } else
            model.addAttribute("customers", singletonList(customerFound));

        return "index";
    }

}
