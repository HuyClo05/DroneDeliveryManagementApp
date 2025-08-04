package DroneDeliveryApp.src.domain;

import DroneDeliveryApp.src.model.enums.IdType;
import DroneDeliveryApp.src.domain.Location;

public class DroneBase {
    private final IdentificationNumber baseId;
    private final Location location;
    private final String name;
    private final int capacity;

    public DroneBase(Location location,
              String name,
              int capacity){

        this.baseId = new IdentificationNumber(IdType.BASE);
        this.location = location;
        this.name = name;
        this.capacity = capacity;
    }

    // Getters
    public String getBaseId() { return baseId.getId(); }
    public Location getLocation() { return location; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
}
