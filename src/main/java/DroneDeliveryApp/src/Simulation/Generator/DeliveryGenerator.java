package DroneDeliveryApp.src.Simulation.Generator;

import DroneDeliveryApp.src.domain.Delivery;
import DroneDeliveryApp.src.domain.ShippingPackage;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomStartEndDateGenerator;

import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeliveryGenerator {
    private final RandomStartEndDateGenerator startEndDateGenerator;
    private final List<Delivery> deliveries;

    public DeliveryGenerator(List<ShippingPackage> packages, Random random) {
        this.startEndDateGenerator = new RandomStartEndDateGenerator(2021, 1, 1, random);
        this.deliveries = generateDeliveries(packages);
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
        return new ArrayList<>(this.deliveries);
    }
}
