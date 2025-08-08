package com.huyvo.dronedeliverymanagementapp;

import com.huyvo.dronedeliverymanagementapp.classes.classValidator.ValidationException;
import com.huyvo.dronedeliverymanagementapp.classes.domains.Delivery;
import com.huyvo.dronedeliverymanagementapp.classes.domains.Drone;
import com.huyvo.dronedeliverymanagementapp.classes.domains.Location;
import com.huyvo.dronedeliverymanagementapp.classes.domains.ShippingPackage;
import com.huyvo.dronedeliverymanagementapp.classes.domains.user.Customer;
import com.huyvo.dronedeliverymanagementapp.generator.*;
import com.huyvo.dronedeliverymanagementapp.repository.*;

import java.util.List;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.jetbrains.annotations.NotNull;

@Component
public class DataInitializer implements CommandLineRunner{
    private final LocationRepository locationRepository;
    private final CustomerRepository customerRepository;
    private final ShippingPackageRepository shippingPackageRepository;
    private final DeliveryRepository deliveryRepository;
    private final DroneRepository droneRepository;

    public DataInitializer(LocationRepository locationRepository,
                           CustomerRepository customerRepository,
                           ShippingPackageRepository shippingPackageRepository,
                           DeliveryRepository deliveryRepository,
                           DroneRepository droneRepository) {

        this.locationRepository = locationRepository;
        this.customerRepository = customerRepository;
        this.shippingPackageRepository = shippingPackageRepository;
        this.deliveryRepository = deliveryRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        setUpEnvironment(2, 10, 22.7f, 1000000f);
    }

    public void setUpEnvironment(int customerCount,
                                 int droneCount,
                                 float maxWeight,
                                 float maxMileage) throws ValidationException {

        Random random = new Random();
        DroneGenerator droneGenerator = new DroneGenerator(droneCount, maxWeight, maxMileage, random);
        CustomerGenerator senderGenerator = new CustomerGenerator(customerCount, random);
        CustomerGenerator recipientGenerator = new CustomerGenerator(customerCount, random);



        List<Location> senderLocationList = senderGenerator.getCustomerLocationList();
        List<Location> recipientLocationList = recipientGenerator.getCustomerLocationList();
        locationRepository.saveAll(senderLocationList);
        locationRepository.saveAll(recipientLocationList);

        List<Customer> senderList = senderGenerator.getCustomerList();
        List<Customer> recipientList = senderGenerator.getCustomerList();
        customerRepository.saveAll(senderList);
        customerRepository.saveAll(recipientList);

        List<ShippingPackage> packageList = setUpPackages(senderList, recipientList, maxWeight, random);
        shippingPackageRepository.saveAll(packageList);

        List<Delivery> deliveryList = setUpDeliveries(packageList, random);
        deliveryRepository.saveAll(deliveryList);
    }

    private @NotNull List<ShippingPackage> setUpPackages(List<Customer> senderList,
                                                         List<Customer> recipientList,
                                                         float maxWeight,
                                                         Random random) {

        PackageGenerator packageGenerator = new PackageGenerator(senderList.size(),
                                                                maxWeight,
                                                                senderList,
                                                                recipientList,
                                                                random);
        return packageGenerator.getPackageList();
    }

    private List<Delivery> setUpDeliveries(List<ShippingPackage> packageList, Random random) {
        DeliveryGenerator deliveryGenerator = new DeliveryGenerator(packageList, random);
        return deliveryGenerator.getDeliveryList();
    }
}
