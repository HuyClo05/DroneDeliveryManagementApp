package com.huyvo.dronedeliverymanagementapp.generator;

import com.huyvo.dronedeliverymanagementapp.classes.domains.Delivery;
import com.huyvo.dronedeliverymanagementapp.classes.domains.ShippingPackage;
import com.huyvo.dronedeliverymanagementapp.generator.Util.RandomStartEndDateGenerator;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeliveryGenerator {
    private final RandomStartEndDateGenerator startEndDateGenerator;
    private final List<Delivery> deliveryList;

    public DeliveryGenerator(List<ShippingPackage> packages, Random random) {
        this.startEndDateGenerator = new RandomStartEndDateGenerator(2021, 1, 1, random);
        this.deliveryList = generateDeliveries(packages);
    }

    public List<Delivery> generateDeliveries(@NotNull List<ShippingPackage> packages) {
        int deliveryNum = packages.size();
        List<Delivery> deliveries = new ArrayList<>(deliveryNum);

        for (ShippingPackage newPackage : packages) {
            LocalDateTime startTime = startEndDateGenerator.getStartTime();
            LocalDateTime endTime = startEndDateGenerator.getEndTime();
            Delivery newDelivery = new Delivery(newPackage, startTime, endTime);
            deliveries.add(newDelivery);
        }
        return deliveries;
    }

    // Getters
    public List<Delivery> getDeliveryList() {
        return this.deliveryList;
    }
}
