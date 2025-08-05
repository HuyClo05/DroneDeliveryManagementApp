package DroneDeliveryApp.src.Simulation.Generator;

import java.util.Random;

import DroneDeliveryApp.src.Simulation.Generator.Util.RandomEmailGenerator;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomNameGenerator;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomPhoneGenerator;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomPasswordGenerator;
import DroneDeliveryApp.src.domain.Location;
import DroneDeliveryApp.src.domain.ShippingPackage;
import DroneDeliveryApp.src.domain.user.Customer;
import org.jetbrains.annotations.NotNull;

/**
 * The CustomerGenerator class is responsible for generating an array of sender and recipient
 * customers with associated location details and assigning randomly generated packages between
 * them. This class facilitates the creation and association of customers, their respective
 * locations, and the assignments of their owned packages.
 * <p>
 * The generated customer instances and their corresponding packages can be accessed via the
 * provided getter methods.
 */
final public class CustomerGenerator {
    private final Random random;
    private final LocationGenerator senderLocationGenerator;
    private final LocationGenerator recipientLocationGenerator;
    private final Customer[] senderList;
    private final Customer[] recipientList;
    private final PackageGenerator packageGenerator;

    /**
     * Constructs a new CustomerGenerator instance, which creates a list of sender customers,
     * a list of recipient customers, and initializes a package generator to handle packages
     * between those customers.
     *
     * @param customerNum the number of customers to generate; must be a positive integer
     * @param maxWeight the maximum weight for any package; must be a positive float
     * @param random a Random object used for generating random values; must not be null
     * @throws IllegalArgumentException if customerNum is less than or equal to 0
     * @throws IllegalArgumentException if random is null
     */
    public CustomerGenerator (int customerNum, float maxWeight, Random random){
        if (customerNum <= 0) {
            throw new IllegalArgumentException("customerNum must be positive");
        }
        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }

        this.random = random;
        this.senderLocationGenerator = setupLocation(customerNum, "sender");
        this.recipientLocationGenerator = setupLocation(customerNum, "recipient");
        this.senderList = generateCustomer(customerNum, recipientLocationGenerator);
        this.recipientList = generateCustomer(customerNum, recipientLocationGenerator);
        this.packageGenerator = new PackageGenerator(customerNum,
                                                    maxWeight,
                                                    senderList,
                                                    recipientList,
                                                    senderLocationGenerator.getLocationList(),
                                                    recipientLocationGenerator.getLocationList(),
                                                    random);
    }

    private @NotNull LocationGenerator setupLocation (int customerNum, String userType) {
        LocationGenerator locationGenerator = new LocationGenerator(customerNum,
                                                                    null,
                                                                    null,
                                                                    null,
                                                                    null,
                                                                    null,
                                                                    null,
                                                                    random);
        if (userType.equals("sender")) {
            locationGenerator.setDefaultAddressLine("Sender_AddressLine1", "Sender_AddressLine2");
        }
        else if (userType.equals("recipient")) {
            locationGenerator.setDefaultAddressLine("Recipient_AddressLine1", "Recipient_AddressLine2");
        }
        else {
            throw new IllegalArgumentException("User type must be either sender or recipient");
        }
        return locationGenerator;
    }

    /**
     * Generates an array of Customer objects based on the specified number of customers and user type.
     * Each Customer object is populated with a name, email, phone number, password, and location,
     * which are randomly generated.
     *
     * @param customerNum the number of customers to generate; must be a positive integer
     * @param locationGenerator the locationGenerator you choose
     * @return an array of Customer objects with randomly generated details
     * @throws IllegalArgumentException if userType is not "sender" or "recipient"
     */
    private Customer [] generateCustomer (int customerNum, LocationGenerator locationGenerator) {
        RandomNameGenerator nameGenerator = new RandomNameGenerator(customerNum, null, null, random);
        RandomEmailGenerator emailGenerator = new RandomEmailGenerator(customerNum, nameGenerator.getNameList(), null , random);
        RandomPhoneGenerator phoneGenerator = new RandomPhoneGenerator(customerNum, random);
        RandomPasswordGenerator passwordGenerator = new RandomPasswordGenerator(customerNum, random);

        Customer[] customers = new Customer[customerNum];
        for (int i = 0; i < customerNum; i++) {
            String newName = nameGenerator.getNameList()[i];
            String newEmail = emailGenerator.getEmailList()[i];
            String newPhone = phoneGenerator.getPhoneList()[i];
            String newPassword = passwordGenerator.getPasswordList()[i];
            Location newLocation = locationGenerator.getLocationList()[i];

            Customer newCustomer = new Customer(newName,
                                                newEmail,
                                                newPhone,
                                                newPassword,
                                                newLocation);
            customers[i] = newCustomer;
        }
        return customers;
    }

    // Getters
    public PackageGenerator getPackageGenerator() { return this.packageGenerator; }
    public Customer[] getRecipientList() { return this.recipientList.clone(); }
    public Customer[] getSenderList() { return this.senderList.clone(); }
}