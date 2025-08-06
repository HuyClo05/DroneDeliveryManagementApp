package DroneDeliveryApp.src.Simulation.Generator.Util;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * RandomPhoneGenerator is a utility class designed to generate random phone numbers
 * and provide them in an easily accessible manner.
 * It allows the generation of a specific number of random phone numbers in a ten-digit format.
 */
public final class RandomPhoneGenerator {
    private final Random random;
    private final List<String> phoneList;

    /**
     * Constructs a RandomPhoneGenerator object that generates a specified number of random phone numbers
     * using the provided Random instance for number generation.
     *
     * @param phoneNum the number of random phone numbers to generate; must be a positive integer
     * @param random the Random instance used for generating random numbers
     * @throws IllegalArgumentException if the phone number count is non-positive
     */
    public RandomPhoneGenerator(int phoneNum, Random random) {
        this.random = random;
        this.phoneList = generatePhone(phoneNum);
    }

    /**
     * Generates an array of random phone numbers in the format "XXX-XXX-XXXX".
     * Each phone number is composed of a random area code, prefix, and line number.
     * The area codes and prefixes avoid ranges starting with 0 or 1.
     *
     * @param phoneNum the number of phone numbers to generate; must be a positive integer
     * @return an ArrayList of generated phone numbers
     * @throws IllegalArgumentException if the phone number count is non-positive
     */
    public @NotNull List<String> generatePhone(int phoneNum) {
        if (phoneNum <= 0) {
            throw new IllegalArgumentException("Number of phone numbers must be positive");
        }

        List<String> phones = new ArrayList<>(phoneNum);
        while (phones.size() < phoneNum) {
            String areaCode = String.format("%03d", random.nextInt(800) + 200);
            String prefix = String.format("%03d", random.nextInt(743) + 200);
            String lineNumber = String.format("%04d", random.nextInt(10000));
            String newPhoneNumber =  areaCode + "-" + prefix + "-" + lineNumber;
            phones.add(newPhoneNumber);
        }
        return phones;
    }

    // Getters
    public List<String> getPhoneList() {
        return new ArrayList<>(this.phoneList);
    }
}
