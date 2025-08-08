package com.huyvo.dronedeliverymanagementapp.generator;

import com.huyvo.dronedeliverymanagementapp.classes.domains.Drone;
import com.huyvo.dronedeliverymanagementapp.classes.classValidator.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DroneGenerator {
    private final Random random;
    private final List<Drone> droneList;

    public DroneGenerator(int droneNum, float maxPayload, float maxMileage, Random random) throws ValidationException {
        this.random = random;
        this.droneList = generateDrones(droneNum, maxPayload, maxMileage);
    }

    public List<Drone> generateDrones(int droneNum, float maxPayload, float maxMileage) throws ValidationException {
        float[] payloads = randomPayloads(droneNum, maxPayload);
        float[] mileages = randomMileages(droneNum, maxMileage);
        int[] batteryLevels = randomBatteryLevels(droneNum);

        List<Drone> drones = new ArrayList<>(droneNum);
        for (int i = 0; i < droneNum; i++){
            float newPayload = payloads[i];
            float newMileages = mileages[i];
            int newBatteryLevel = batteryLevels[i];
            Drone newDrone = new Drone(newPayload, newBatteryLevel, newMileages);
            drones.add(newDrone);
        }
        return drones;
    }

    public float[] randomPayloads (int droneNum, float maxPayload) {
        float[] payloads = new float[droneNum];
        for (int i = 0; i < droneNum; i++) {
            payloads[i] = 0.5f + random.nextFloat() * (maxPayload - 0.5f);
        }
        return payloads;
    }

    public float[] randomMileages(int droneNum, float maxMileage) {
        float[] mileages = new float[droneNum];
        float minMileage = 5.0f;
        float cappedMax = Math.min(maxMileage, 50.0f); // Ensure max doesn't exceed 50.0f
        for (int i = 0; i < droneNum; i++) {
            mileages[i] = minMileage + random.nextFloat() * (cappedMax - minMileage);
        }
        return mileages;
    }

    public int[] randomBatteryLevels(int droneNum) {
        int[] batteryLevels = new int[droneNum];
        for (int i = 0; i < droneNum; i++) {
            batteryLevels[i] = 1 + random.nextInt(100 - 1);
        }
        return batteryLevels;
    }

    // Getters
    public List<Drone> getDroneList() {
        return this.droneList;
    }
}
