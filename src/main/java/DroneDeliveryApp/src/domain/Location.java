package DroneDeliveryApp.src.domain;

import java.util.Objects;

import DroneDeliveryApp.src.model.enums.IdType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

/**
 * Represents a geographic location with detailed address information
 * including latitude and longitude coordinates.
 */
@Embeddable
public class Location {
    @EmbeddedId
    private IdentificationNumber locationId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipcode;
    private String country;
    private float latitude;
    private float longitude;

    public Location(){}

    public Location(String addressLine1,
                    String addressLine2,
                    String city,
                    String state,
                    String zipcode,
                    String country,
                    float latitude,
                    float longitude) {

        this.locationId = new IdentificationNumber(IdType.LOCATION);
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public String getLocationId() { return this.locationId.getId(); }
    public String getAddressLine1() { return this.addressLine1; }
    public String getAddressLine2() { return this.addressLine2; }
    public String getCity() { return this.city; }
    public String getState() { return this.state; }
    public String getZipcode() { return this.zipcode; }
    public String getCountry() { return this.country; }
    public float getLatitude() { return this.latitude; }
    public float getLongitude() { return this.longitude; }

    // Setters
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    // Methods
    /**
     * Give out latitude and longitude
     *
     * @return a float[2] with latitude and longitude in it
     */
    public float[] getCoordinates() {
        return new float[]{this.latitude, this.longitude};
    }

    /**
     * Returns the full address as a formatted string
     *
     * @return String containing the complete address
     */
    public String getFormattedAddress() {
        StringBuilder address = new StringBuilder(addressLine1);
        if (addressLine2 != null && !addressLine2.isEmpty()) {
            address.append(", ").append(addressLine2);
        }
        address.append(", ")
                .append(city).append(", ")
                .append(state).append(" ")
                .append(zipcode).append(", ")
                .append(country);
        return address.toString();
    }

    @Override
    public String toString() {
        return String.format("Location[id=%s, address='%s', lat=%.6f, lon=%.6f]",
                locationId.getId(),
                getFormattedAddress(),
                latitude,
                longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Float.compare(location.latitude, latitude) == 0 &&
                Float.compare(location.longitude, longitude) == 0 &&
                Objects.equals(locationId, location.locationId) &&
                Objects.equals(addressLine1, location.addressLine1) &&
                Objects.equals(addressLine2, location.addressLine2) &&
                Objects.equals(city, location.city) &&
                Objects.equals(state, location.state) &&
                Objects.equals(zipcode, location.zipcode) &&
                Objects.equals(country, location.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, addressLine1, addressLine2, city, state, zipcode, country, latitude, longitude);
    }
}