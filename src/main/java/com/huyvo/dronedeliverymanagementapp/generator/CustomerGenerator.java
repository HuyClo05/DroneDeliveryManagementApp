package com.huyvo.dronedeliverymanagementapp.generator;

import com.huyvo.dronedeliverymanagementapp.generator.Util.RandomEmailGenerator;
import com.huyvo.dronedeliverymanagementapp.generator.Util.RandomNameGenerator;
import com.huyvo.dronedeliverymanagementapp.generator.Util.RandomPhoneGenerator;
import com.huyvo.dronedeliverymanagementapp.generator.Util.RandomPasswordGenerator;
import com.huyvo.dronedeliverymanagementapp.classes.domains.Location;
import com.huyvo.dronedeliverymanagementapp.classes.domains.user.Customer;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private final List<Customer> customerList;
    private final List<Location> customerLocationList;

    /**
     * Constructs a new CustomerGenerator instance, which creates a list of sender customers,
     * a list of recipient customers, and initializes a package generator to handle packages
     * between those customers.
     *
     * @param customerNum the number of customers to generate; must be a positive integer
     * @param random a Random object used for generating random values; must not be null
     * @throws IllegalArgumentException if customerNum is less than or equal to 0
     * @throws IllegalArgumentException if random is null
     */
    public CustomerGenerator (int customerNum, Random random){
        if (customerNum <= 0) {
            throw new IllegalArgumentException("customerNum must be positive");
        }
        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }

        LocationGenerator senderLocationGenerator = new LocationGenerator(customerNum,
                                                    null,
                                                    null,
                                                    null,
                                                    null,
                                                    null,
                                                    null,
                                                    random);

        this.customerLocationList = senderLocationGenerator.getLocationList();
        this.customerList = generateCustomer(customerNum, customerLocationList, random);
    }

    /**
     * Generates an array of Customer objects based on the specified number of customers and user type.
     * Each Customer object is populated with a name, email, phone number, password, and location,
     * which are randomly generated.
     *
     * @param customerNum the number of customers to generate; must be a positive integer
     * @param locationList the list of Location you want to add to the customers
     * @return an ArrayList of Customer objects with randomly generated details
     * @throws IllegalArgumentException if userType is not "sender" or "recipient"
     */
    private @NotNull List<Customer> generateCustomer (int customerNum, List<Location> locationList, Random random) {
        RandomNameGenerator nameGenerator = new RandomNameGenerator(customerNum, null, null, random);
        RandomEmailGenerator emailGenerator = new RandomEmailGenerator(customerNum, nameGenerator.getNameList(), null , random);
        RandomPhoneGenerator phoneGenerator = new RandomPhoneGenerator(customerNum, random);
        RandomPasswordGenerator passwordGenerator = new RandomPasswordGenerator(customerNum, random);

        List<Customer> customers = new ArrayList<>(customerNum);
        for (int i = 0; i < customerNum; i++) {
            String newName = nameGenerator.getNameList().get(i);
            String newEmail = emailGenerator.getEmailList().get(i);
            String newPhone = phoneGenerator.getPhoneList().get(i);
            String newPassword = passwordGenerator.getPasswordList().get(i);
            Location newLocation = locationList.get(i);

            Customer newCustomer = new Customer(newName,
                                                newEmail,
                                                newPhone,
                                                newPassword,
                                                newLocation);
            customers.add(newCustomer);
        }
        return customers;
    }

    // Getters
    @Contract(value = " -> new", pure = true)
    public @NotNull List<Location> getCustomerLocationList() {
        return this.customerLocationList;
    }

    @Contract(value = " -> new", pure = true)
    public @NotNull List<Customer> getCustomerList() {
        return this.customerList;
    }
}