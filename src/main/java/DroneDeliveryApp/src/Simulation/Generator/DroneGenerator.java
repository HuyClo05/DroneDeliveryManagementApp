package DroneDeliveryApp.src.Simulation.Generator;

import java.util.Random;
import DroneDeliveryApp.src.domain.Drone;

public class DroneGenerator {
    Random random = new Random();
    private int droneNum;
    private Drone[] droneList;
    private float maxPayload;


    public DroneGenerator(int droneNum) {
        this.droneNum = droneNum;
        this.droneList = new Drone[droneNum];
        this.maxPayload = 22.7f;
    }

    public float[] randomPayload (){
        int payload = random.nextInt((int) maxPayload);
        float[] payloadArray = new float[payload];

        for (int i = 0; i < payload; i++) {
            payloadArray[i] = random.nextFloat();
        }
        return payloadArray;
    }
}
