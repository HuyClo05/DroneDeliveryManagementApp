package com.huyvo.dronedeliverymanagementapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import DroneDeliveryApp.src.Simulation.Generator.CustomerGenerator;
import DroneDeliveryApp.src.domain.user.Customer;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final Random random = new Random();
    private final CustomerGenerator customerGenerator = new CustomerGenerator(100, 22.7f, random);
    private final Customer[] customersList = customerGenerator.getRecipientList();

    @GetMapping
    public Customer[] getAllCustomers() {
        return customersList;
    }
}

