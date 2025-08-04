package DroneDeliveryApp.src.Simulation.Generator.Util;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * RandomEmailGenerator is a utility class designed to generate random email addresses
 * using a predefined list of names and domains.
 */
public final class RandomEmailGenerator {
    private final Random random;
    private final String[] emailList;
    private final String[] domains;
    private static final String[] DEFAULT_DOMAINS = {
            "gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com", "aol.com"
    };

    /**
     * Constructs a RandomEmailGenerator object that generates random email addresses.
     * The email addresses are created using a specified number of emails, a list of names,
     * and either custom or default domain options.
     *
     * @param emailNum the number of email addresses to generate; must be a positive integer
     * @param nameList the list of names to use for generating email addresses; must not be null or empty
     * @param customDomains an optional array of custom domain names; if null or empty, default domains will be used
     * @param random the Random instance used for generating random indices for domain selection
     * @throws IllegalArgumentException if the name list is null or empty, or if the email number is non-positive
     */
    public RandomEmailGenerator(int emailNum, String[] nameList, String[] customDomains, Random random) {
        this.domains = customDomains != null && customDomains.length > 0 ? customDomains : DEFAULT_DOMAINS;
        this.random = random;
        this.emailList = generateEmail(emailNum, nameList);
    }

    /**
     * Generates an array of email addresses based on the provided number of emails to generate
     * and the corresponding list of usernames. Each email is formed by converting the names to
     * lowercase, replacing spaces with underscores, and appending a randomly selected domain.
     *
     * @param emailNum the number of email addresses to generate; must be a positive integer
     * @param nameList the list of names to use for generating email addresses; must not be null or empty
     * @return an array of generated email addresses
     * @throws IllegalArgumentException if the name list is null or empty, or if the email number is non-positive
     */
    public @NotNull String[] generateEmail(int emailNum, String[] nameList) {
        if (nameList == null || nameList.length == 0) {
            throw new IllegalArgumentException("Name list cannot be null or empty");
        }
        if (emailNum <= 0) {
            throw new IllegalArgumentException("Email number must be positive");
        }

        String[] emails = new String[emailNum];
        for (int i = 0; i < emailNum; i++) {
            StringBuilder email = new StringBuilder();
            String name = nameToEmailUsername(nameList[i]);
            String domain = domains[random.nextInt(domains.length)];
            email.append(name).append("@").append(domain);
            emails[i] = email.toString();
        }
        return emails;
    }

    private String nameToEmailUsername(String name) {
        return name.toLowerCase()
                .replace(" ", ".")
                .replaceAll("[^a-zA-Z0-9._-]", "");
    }

    //Getters
    public String[] getEmailList() {
        return emailList.clone();
    }
}
