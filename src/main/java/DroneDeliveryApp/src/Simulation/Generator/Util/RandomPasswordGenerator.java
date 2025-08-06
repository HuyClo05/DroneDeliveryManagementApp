package DroneDeliveryApp.src.Simulation.Generator.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomPasswordGenerator is a utility class for generating a predefined number of random passwords.
 * Each password is composed of alphanumeric characters with a variable length between 8 and 17 characters.
 * The passwords are generated upon object instantiation and stored in an array for retrieval.
 */
public final class RandomPasswordGenerator {
    private final Random random;
    private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final List<String> passwordList;

    /**
     * Constructs a RandomPasswordGenerator instance with the specified number of passwords.
     * The passwords are generated based on a random alphanumeric character set and stored
     * in an internal list for retrieval.
     *
     * @param passwordNum the number of passwords to generate; must be a positive integer
     * @param random      an instance of Random used for generating random values
     */
    public RandomPasswordGenerator(int passwordNum, Random random) {
        this.random = random;
        this.passwordList = generatePassword(passwordNum);
    }

    /**
     * Generates an array of random alphanumeric passwords. Each password has a randomly
     * determined length between 8 and 17 characters.
     *
     * @param passwordNum the number of passwords to generate; must be a positive integer
     * @return an ArrayList of randomly generated passwords
     * @throws IllegalArgumentException if the specified number of passwords is non-positive
     */
    public List<String> generatePassword(int passwordNum) {
        List<String> passwords = new ArrayList<>(passwordNum);

        for (int i = 0; i < passwordNum; i++) {
            StringBuilder password = new StringBuilder();
            int passwordLength = random.nextInt(10) + 8;
            for (int j = 0; j < passwordLength; j++) {
                password.append(ALPHANUMERIC_CHARS.charAt(random.nextInt(ALPHANUMERIC_CHARS.length())));
            }
            passwords.add(password.toString());
        }
        return passwords;
    }

    // Getters
    public List<String> getPasswordList() {
        return new ArrayList<>(this.passwordList);
    }
}
