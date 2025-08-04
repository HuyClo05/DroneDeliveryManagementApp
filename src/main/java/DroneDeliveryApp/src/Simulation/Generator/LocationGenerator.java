package DroneDeliveryApp.src.Simulation.Generator;

import java.util.Random;
import DroneDeliveryApp.src.domain.Location;

/**
 * The LocationGenerator class is responsible for generating a list of random locations.
 * Each location comprises geographical coordinates (latitude and longitude)
 * and an address composed of various fields such as address lines, city, state, zip code, and country.
 * Default values for the address fields can be configured before generating locations.
 * <p>
 * The class ensures that the latitude and longitude of each location are random but within specific bounds:
 * - Latitude is in the range [0, 180).
 * - Longitude is in the range [0, 360).
 * <p>
 * Once the locations are generated, they remain immutable as this class provides
 * only getter access to the generated location list.
 */
public final class LocationGenerator {
    private final Random random;
    private String defaultAddressLine1;
    private String defaultAddressLine2;
    private String defaultCity;
    private String defaultState;
    private String defaultZipCode;
    private String defaultCountry;
    private static final float latitudeRange = 180f;
    private static final float longitudeRange = 360f;
    private final Location[] locationList;

    /**
     * Constructs an instance of the LocationGenerator class to generate a list of Location objects
     * with specified or default address attributes and random geographic coordinates.
     *
     * @param locationNum the number of Location objects to generate; must be a positive integer.
     * @param addressLine1 the first line of the address. If null, a default value of "TEST_ADDRESS_LINE1" is used.
     * @param addressLine2 the second line of the address. If null, a default value of "TEST_ADDRESS_LINE2" is used.
     * @param city the city of the address. If null, a default value of "TEST_CITY" is used.
     * @param state the state of the address. If null, a default value of "TEST_STATE" is used.
     * @param zipCode the ZIP/postal code of the address. If null, a default value of "TEST_ZIPCODE" is used.
     * @param country the country of the address. If null, a default value of "TEST_COUNTRY" is used.
     * @param random an instance of {@link Random} used to generate random geographic coordinates.
     * @throws IllegalArgumentException if locationNum is less than or equal to zero.
     */
    public LocationGenerator(int locationNum,
                             String addressLine1,
                             String addressLine2,
                             String city,
                             String state,
                             String zipCode,
                             String country,
                             Random random) {

        this.random = random;
        this.defaultAddressLine1 = addressLine1 != null ? addressLine1 : "TEST_ADDRESS_LINE1";
        this.defaultAddressLine2 = addressLine2 != null ? addressLine2 : "TEST_ADDRESS_LINE2";
        this.defaultCity = city != null ? city : "TEST_CITY";
        this.defaultState = state != null ? state : "TEST_STATE";
        this.defaultZipCode = zipCode != null ? zipCode : "TEST_ZIPCODE";
        this.defaultCountry = country != null ? country : "TEST_COUNTRY";
        this.locationList = generateLocations(locationNum);
    }

    /**
     * Generates an array of Location objects with random geographic coordinates
     * and default address details.
     *
     * @param locationNum the number of Location objects to generate; must be a positive integer
     * @return an array of Location objects, each populated with default address details
     *         and random latitude and longitude values
     * @throws IllegalArgumentException if locationNum is less than or equal to zero
     */
    private Location[] generateLocations (int locationNum){
        if (locationNum <= 0) {
            throw new IllegalArgumentException("Number of locations must be positive");
        }

        Location[] locations = new Location[locationNum];
        for (int i = 0; i < locationNum; i ++) {
            float newLatitude = (random.nextFloat() * latitudeRange) - 90f;
            float newLongitude = (random.nextFloat() * longitudeRange) - 180f;

            locations[i] = new Location(this.defaultAddressLine1,
                                        this.defaultAddressLine2,
                                        this.defaultCity,
                                        this.defaultState,
                                        this.defaultZipCode,
                                        this.defaultCountry,
                                        newLatitude,
                                        newLongitude);
        }
        return locations;
    }

    // Getters
    public Location[] getLocationList() { return this.locationList.clone(); }

    // Setters
    public void setDefaultAddressLine(String addressLine1, String addressLine2){
        this.defaultAddressLine1 = addressLine1;
        this.defaultAddressLine2 = addressLine2;
    }

    public void setDefaultCity(String city){
        this.defaultCity = city;
    }

    public void setDefaultState(String state){
        this.defaultState = state;
    }

    public void setDefaultZipCode(String zipCode){
        this.defaultZipCode = zipCode;
    }

    public void setDefaultCountry(String country){
        this.defaultCountry = country;
    }
}
