package DroneDeliveryApp.src.Simulation.Generator;

import java.util.Random;

import DroneDeliveryApp.src.Simulation.Generator.Util.RandomEmailGenerator;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomNameGenerator;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomPhoneGenerator;
import DroneDeliveryApp.src.Simulation.Generator.Util.RandomPasswordGenerator;
import DroneDeliveryApp.src.domain.Location;
import DroneDeliveryApp.src.domain.ShippingPackage;
import DroneDeliveryApp.src.domain.user.Customer;

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
        this.senderList = generateCustomer(customerNum, "sender");
        this.recipientList = generateCustomer(customerNum, "recipient");
        this.packageGenerator = new PackageGenerator(customerNum,
                                                    maxWeight,
                                                    senderList,
                                                    recipientList,
                                                    null,
                                                    null,
                                                    random);
        assignPackageCustomerRelationship ();
    }
    
    /**
     * Assigns a relationship between sender customers, recipient customers, and shipping packages.
     * <p>
     * This method retrieves a list of generated shipping packages and iterates through them, 
     * assigning a specific sender and recipient customer to each package. Additionally, it 
     * associates the package with the respective sender and recipient customers. The mapping 
     * between customers and packages follows a one-to-one correspondence defined by the 
     * indices of the senderList, recipientList, and the package list.
     * </p>
     * <p>
     * The relationships established are as follows:
     * <ul>
     *   <li>Each package is assigned a sender and recipient customer.</li>
     *   <li>Each sender and recipient customer is assigned the same package.</li>
     * </ul>
     * </p>
     * <p>
     * <b>Preconditions:</b>
     * <ul>
     *   <li>The senderList, recipientList, and package list must have non-null and equal lengths.</li>
     *   <li>Each index in the senderList and recipientList corresponds to the same index in the package list.</li>
     * </ul>
     * </p>
     * <p>
     * <b>Postconditions:</b>
     * <ul>
     *   <li>Each shipping package will have its sender and recipient fields set to the corresponding customers.</li>
     *   <li>Each sender and recipient customer will have its user package field set to the corresponding package.</li>
     * </ul>
     * </p>
     * <p>
     * This method assumes that the arrays {@code senderList}, {@code recipientList}, and the result of 
     * {@code packageGenerator.getPackageList()} have been initialized and populated before invoking this method.
     * </p>
     */
    private void assignPackageCustomerRelationship () {
        ShippingPackage[] packages = this.packageGenerator.getPackageList();
        for (int i = 0; i < packages.length; i++){
            Customer sender = this.senderList[i];
            Customer recipient = this.recipientList[i];
            sender.setUserPackage(packages[i]);
            recipient.setUserPackage(packages[i]);
            packages[i].setSender(sender);
            packages[i].setRecipient(recipient);
        }
    }

    private LocationGenerator setupLocation (int customerNum, String userType) {
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
     * @param userType the type of user for which customers are generated, either "sender" or "recipient"
     * @return an array of Customer objects with randomly generated details
     * @throws IllegalArgumentException if userType is not "sender" or "recipient"
     */
    private Customer [] generateCustomer (int customerNum, String userType) {
        LocationGenerator locationGenerator = setupLocation(customerNum, userType);
        RandomNameGenerator nameGenerator = new RandomNameGenerator(customerNum, null, null, random);
        RandomEmailGenerator emailGenerator = new RandomEmailGenerator(customerNum, nameGenerator.getNameList(), null , random);
        RandomPhoneGenerator phoneGenerator = new RandomPhoneGenerator(customerNum, random);
        RandomPasswordGenerator passwordGenerator = new RandomPasswordGenerator(customerNum, random);

        Customer[] customers = new Customer[customerNum];
        for (int i = 0; i < customerNum; i++) {
            String newName = nameGenerator.getNameList()[i];
            String newEmail = emailGenerator.getEmailList()[i];
            String newPhone = phoneGenerator.getPhoneList()[i];
            String password = passwordGenerator.getPasswordList()[i];
            Location newLocation = locationGenerator.getLocationList()[i];

            Customer newCustomer = new Customer(newName,
                                                newEmail,
                                                newPhone,
                                                password,
                                                newLocation,
                                                null);
            customers[i] = newCustomer;
        }
        return customers;
    }

    // Getters
    public PackageGenerator getPackageGenerator() { return this.packageGenerator; }
    public Customer[] getRecipientList() { return this.recipientList.clone(); }
    public Customer[] getSenderList() { return this.senderList.clone(); }
}