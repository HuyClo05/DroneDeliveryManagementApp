package DroneDeliveryApp.src.domain;

import DroneDeliveryApp.src.model.enums.IdType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

@Embeddable
public class DroneBase {
    @EmbeddedId
    private IdentificationNumber baseId;
    private Location location;
    private String name;
    private int capacity;

    public DroneBase(){}

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
