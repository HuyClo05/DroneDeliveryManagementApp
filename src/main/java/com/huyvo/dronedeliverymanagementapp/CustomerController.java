package com.huyvo.dronedeliverymanagementapp;

import DroneDeliveryApp.src.Simulation.Generator.PackageGenerator;
import DroneDeliveryApp.src.domain.ShippingPackage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import DroneDeliveryApp.src.Simulation.Generator.CustomerGenerator;
import DroneDeliveryApp.src.domain.user.Customer;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    Random random = new Random();
    int NUM_CUSTOMERS = 1;

    CustomerGenerator customerGenerator = new CustomerGenerator(NUM_CUSTOMERS, random);
    List<Customer> senderList = customerGenerator.getSenderList();
    List<Customer> recipientList = customerGenerator.getRecipientList();

    PackageGenerator packageGenerator = new PackageGenerator(NUM_CUSTOMERS,
                                                            22.7f,
                                                            senderList,
                                                            recipientList,
                                                            random);
    List<ShippingPackage> packageList = packageGenerator.getPackageList();



    @GetMapping("/senders")
    public List<Customer> getAllSender() {
        return senderList;
    }

    @GetMapping("/recipients")
    public List<Customer> getAllRecipient() {
        return recipientList;
    }

    @GetMapping("/packages")
    public List<ShippingPackage> getAllPackage() {
        return packageList;
    }
}

