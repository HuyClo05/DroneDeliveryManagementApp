package DroneDeliveryApp.src.Simulation.Generator;

import java.util.Random;
import DroneDeliveryApp.src.domain.Location;
import DroneDeliveryApp.src.domain.ShippingPackage;
import DroneDeliveryApp.src.domain.user.Customer;

/**
 * The PackageGenerator class is responsible for creating an array of random ShippingPackage objects.
 * Each generated package will have a random weight and a randomly generated description string.
 */
final public class PackageGenerator {
    private final Random random;
    private final ShippingPackage[] packageList;
    private final Customer[] sender;
    private final Customer[] recipient;
    private final Location[] pickupLocation;
    private final Location[] deliveryLocation;

    /**
     * Constructs a new instance of the PackageGenerator class, initializing it with the specified parameters
     * for generating a list of shipping packages.
     *
     * @param packageNum the number of shipping packages to generate. Must be a positive integer.
     * @param maxWeight the maximum allowable weight for each shipping package. Must be a positive value.
     * @param sender an array of Customer objects representing the senders of the packages. The array must
     *               contain at least {@code packageNum} elements.
     * @param recipient an array of Customer objects representing the recipients of the packages. The array
     *                  must contain at least {@code packageNum} elements.
     * @param pickupLocation an array of Location objects representing the pickup locations for the packages.
     *                       The array must contain at least {@code packageNum} elements.
     * @param deliveryLocation an array of Location objects representing the delivery locations for the
     *                         packages. The array must contain at least {@code packageNum} elements.
     * @param random a Random instance used to generate random values for package attributes.
     * @throws IllegalArgumentException if any of the input arrays contain fewer elements than {@code packageNum}.
     */
    public PackageGenerator(int packageNum,
                            float maxWeight,
                            Customer [] sender,
                            Customer[] recipient,
                            Location[] pickupLocation,
                            Location[] deliveryLocation,
                            Random random) {

        // Validate array lengths
        if (sender.length < packageNum || recipient.length < packageNum ||
                pickupLocation.length < packageNum || deliveryLocation.length < packageNum) {
            throw new IllegalArgumentException("Input arrays must contain at least " + packageNum + " elements");
        }

        this.random = random;
        this.sender = sender;
        this.recipient = recipient;
        this.pickupLocation = pickupLocation;
        this.deliveryLocation = deliveryLocation;
        this.packageList = generatePackages(packageNum, maxWeight);
    }


    /**
     * Generates an array of {@link ShippingPackage} instances based on the specified number of packages
     * and maximum allowable weight per package.
     *
     * @param packageNum the number of shipping packages to generate. Must be a positive integer.
     * @param maxWeight the maximum allowable weight for each shipping package. Must be a positive value.
     * @return an array of {@link ShippingPackage} objects, each initialized with random attributes and adhering
     *         to the specified constraints.
     * @throws IllegalArgumentException if {@code packageNum} or {@code maxWeight} is less than or equal to zero.
     */
    public ShippingPackage [] generatePackages (int packageNum, float maxWeight) {
        if (packageNum <= 0) {
            throw new IllegalArgumentException("Package count must be positive");
        }
        if (maxWeight <= 0) {
            throw new IllegalArgumentException("Maximum weight must be positive");
        }

        ShippingPackage[] packages = new ShippingPackage[packageNum];
        for (int i = 0; i < packageNum; i++){
            // Ensure sender and receiver name is not matching
            int senderIndex = i % sender.length;
            int recipientIndex;
            do {
                recipientIndex = random.nextInt(recipient.length);
            } while (sender[senderIndex].equals(recipient[recipientIndex]));

            float weight = this.random.nextFloat() * maxWeight;
            String description = generateRandomString(this.random.nextInt(20) + 5);

            packages[i] = new ShippingPackage(weight,
                                            description,
                                            this.sender[senderIndex],
                                            this.recipient[recipientIndex],
                                            this.pickupLocation[i],
                                            this.deliveryLocation[i]);
        }
        return packages;
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(this.random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // Getters
    public ShippingPackage[] getPackageList() { return this.packageList.clone(); }
}
