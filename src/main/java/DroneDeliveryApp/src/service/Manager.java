package DroneDeliveryApp.src.service;

import DroneDeliveryApp.src.domain.Drone;
import DroneDeliveryApp.src.domain.Location;
import DroneDeliveryApp.src.domain.user.Customer;

import java.util.*;

public class Manager {
    private static List<Drone> droneList;
    private static List<Customer> customerList;

    public Manager() {

    }

    public float calculateDistance(Customer customer){
        Location customer_address = customer.getAddress();
        Location customer_destination = customer.getAddress();

        double lat1 = customer_address.getLatitude();
        double lon1 = customer_address.getLongitude();
        double lat2 = customer_destination.getLatitude();
        double lon2 = customer_destination.getLongitude();

        // Radius of the Earth in kilometers
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // in kilometers

        return (float) distance;
    }

    public float distanceDroneHasLeft(Drone drone){
        int droneCurrentBattery = drone.getBatteryLevel();
        float droneMileage = drone.getMileage();
        return  (droneCurrentBattery / 100f) * droneMileage;
    }

    public boolean canDeliver(Drone drone, Customer customer){
        return distanceDroneHasLeft(drone) <= calculateDistance(customer);
    }
}
