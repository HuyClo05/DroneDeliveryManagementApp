package com.huyvo.dronedeliverymanagementapp.generator;

import com.huyvo.dronedeliverymanagementapp.classes.domains.ShippingPackage;
import com.huyvo.dronedeliverymanagementapp.classes.domains.user.Customer;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The PackageGenerator class is responsible for creating an array of random ShippingPackage objects.
 * Each generated package will have a random weight and a randomly generated description string.
 */
final public class PackageGenerator {
    private final Random random;
    private final List<ShippingPackage> packageList;
    private final List<Customer> sender;
    private final List<Customer> recipient;

    /**
     * Constructs a new instance of the PackageGenerator class, initializing it with the specified parameters
     * for generating a list of shipping packages.
     *
     * @param packageNum the number of shipping packages to generate. Must be a positive integer.
     * @param maxWeight the maximum allowable weight for each shipping package. Must be a positive value.
     * @param sender an Arraylist of Customer objects representing the senders of the packages. The array must
     *               contain at least {@code packageNum} elements.
     * @param recipient an Arraylist of Customer objects representing the recipients of the packages. The array
     *                  must contain at least {@code packageNum} elements.
     * @param random a Random instance used to generate random values for package attributes.
     * @throws IllegalArgumentException if any of the input Arraylists contain fewer elements than {@code packageNum}.
     */
    public PackageGenerator(int packageNum,
                            float maxWeight,
                            List<Customer> sender,
                            List<Customer> recipient,
                            Random random) {

        // Validate array lengths
        if (sender.size() != packageNum || recipient.size() != packageNum) {
            throw new IllegalArgumentException("sender and recipient must contain the same number of elements as packageNum");
        }

        this.random = random;
        this.sender = sender;
        this.recipient = recipient;
        this.packageList = generatePackages(packageNum, maxWeight);
    }


    /**
     * Generates an Arraylist of {@link ShippingPackage} instances based on the specified number of packages
     * and maximum allowable weight per package.
     *
     * @param maxWeight the maximum allowable weight for each shipping package. Must be a positive value.
     * @return an Arraylist of {@link ShippingPackage} objects, each initialized with random attributes and adhering
     *         to the specified constraints.
     * @throws IllegalArgumentException if {@code packageNum} or {@code maxWeight} is less than or equal to zero.
     */
    public @NotNull List<ShippingPackage> generatePackages(int packageCount, float maxWeight) {
        if (packageCount <= 0) { throw new IllegalArgumentException("Package count must be positive"); }
        if (maxWeight <= 0) { throw new IllegalArgumentException("Maximum weight must be positive"); }

        List<ShippingPackage> packages = new ArrayList<>(packageCount);

        for (int i = 0; i < packageCount; i++) {
            Customer sender = this.sender.get(i);
            Customer recipient = getRandomRecipientExcluding(sender);

            float weight = random.nextFloat() * maxWeight;
            int descriptionLength = random.nextInt(20) + 5;
            String description = generateRandomString(descriptionLength);

            ShippingPackage newPackage = new ShippingPackage(
                    weight,
                    description,
                    sender,
                    recipient);

            packages.add(newPackage);
        }
        return packages;
    }

    private Customer getRandomRecipientExcluding(Customer excludedSender) {
        Customer recipient;
        do {
            recipient = this.recipient.get(random.nextInt(this.recipient.size()));
        } while (recipient.equals(excludedSender));

        return recipient;
    }

    private @NotNull String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(this.random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // Getters
    @Contract(value = " -> new", pure = true)
    public @NotNull List<ShippingPackage> getPackageList() {
        return this.packageList;
    }
}
