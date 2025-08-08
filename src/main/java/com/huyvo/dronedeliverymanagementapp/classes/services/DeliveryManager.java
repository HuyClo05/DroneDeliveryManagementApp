package com.huyvo.dronedeliverymanagementapp.classes.services;

import com.huyvo.dronedeliverymanagementapp.classes.domains.Drone;
import com.huyvo.dronedeliverymanagementapp.classes.domains.DroneBase;
import com.huyvo.dronedeliverymanagementapp.classes.domains.Location;
import com.huyvo.dronedeliverymanagementapp.classes.domains.ShippingPackage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DeliveryManager {
    private final DroneBase droneBase;
    private final List<Drone> droneList;
    private final List<ShippingPackage> packageList;

    public DeliveryManager(DroneBase dronebase, List<Drone> droneList, List<ShippingPackage> packageList) {
        this.droneBase = dronebase;
        this.droneList = droneList;
        this.packageList = packageList;
    }

    public float calculateDistance(@NotNull ShippingPackage shippingPackage){
        Location baseLocation = this.droneBase.getLocation();
        Location finalLocation = shippingPackage.getDeliveryLocation();

        double lat1 = baseLocation.getLatitude();
        double lon1 = baseLocation.getLongitude();
        double lat2 = finalLocation.getLatitude();
        double lon2 = finalLocation.getLongitude();

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

    public float distanceDroneHasLeft(@NotNull Drone drone){
        int droneCurrentBattery = drone.getBatteryLevel();
        float droneMileage = drone.getMileage();
        return  (droneCurrentBattery / 100f) * droneMileage;
    }

    public boolean canDeliver(Drone drone, ShippingPackage customerPackage){
        return distanceDroneHasLeft(drone) <= calculateDistance(customerPackage);
    }




    //Getters
    public List<Drone> getDroneList() {
        return new ArrayList<>(this.droneList);
    }

    public List<ShippingPackage> getPackageList() {
        return new ArrayList<>(this.packageList);
    }
}
