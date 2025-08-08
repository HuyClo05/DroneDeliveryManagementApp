package com.huyvo.dronedeliverymanagementapp;

import com.huyvo.dronedeliverymanagementapp.classes.domains.ShippingPackage;
import com.huyvo.dronedeliverymanagementapp.classes.domains.user.Customer;
import com.huyvo.dronedeliverymanagementapp.classes.domains.Delivery;
import com.huyvo.dronedeliverymanagementapp.generator.PackageGenerator;
import com.huyvo.dronedeliverymanagementapp.generator.CustomerGenerator;
import com.huyvo.dronedeliverymanagementapp.generator.DeliveryGenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    Random random = new Random();
    int NUM_CUSTOMERS = 1;

    CustomerGenerator senderGenerator = new CustomerGenerator(NUM_CUSTOMERS, random);
    CustomerGenerator recipientGenerator = new CustomerGenerator(NUM_CUSTOMERS, random);
    List<Customer> senderList = senderGenerator.getCustomerList();
    List<Customer> recipientList = recipientGenerator.getCustomerList();

    PackageGenerator packageGenerator = new PackageGenerator(NUM_CUSTOMERS,
                                                            22.7f,
                                                            senderList,
                                                            recipientList,
                                                            random);
    List<ShippingPackage> packageList = packageGenerator.getPackageList();

    DeliveryGenerator deliveryGenerator = new DeliveryGenerator(packageList, random);
    List<Delivery> deliveryList = deliveryGenerator.getDeliveryList();

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

    @GetMapping("/deliveries")
    public List<Delivery> getAllDelivery() {
        return deliveryList;
    }
}

