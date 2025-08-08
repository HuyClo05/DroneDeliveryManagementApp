package com.huyvo.dronedeliverymanagementapp.generator.Util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * RandomEmailGenerator is a utility class designed to generate random email addresses
 * using a predefined list of names and domains.
 */
public final class RandomEmailGenerator {
    private static final List<String> DEFAULT_DOMAINS = new ArrayList<>(
            Arrays.asList("gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "icloud.com", "aol.com")
    );
    private final Random random;
    private final List<String> emailList;
    private final List<String> domains;

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
    public RandomEmailGenerator(int emailNum, List<String> nameList, List<String> customDomains, Random random) {
        this.random = random;
        this.domains = customDomains != null && !customDomains.isEmpty() ? customDomains : DEFAULT_DOMAINS;
        this.emailList = generateEmail(emailNum, nameList);
    }

    /**
     * Generates an Arraylist of email addresses based on the provided number of emails to generate
     * and the corresponding list of usernames. Each email is formed by converting the names to
     * lowercase, replacing spaces with underscores, and appending a randomly selected domain.
     *
     * @param emailNum the number of email addresses to generate; must be a positive integer
     * @param nameList the list of names to use for generating email addresses; must not be null or empty
     * @return an ArrayList of generated email addresses
     * @throws IllegalArgumentException if the name list is null or empty, or if the email number is non-positive
     */
    public @NotNull List<String> generateEmail(int emailNum, List<String> nameList) {
        if (nameList == null || nameList.isEmpty()) {
            throw new IllegalArgumentException("Name list cannot be null or empty");
        }
        if (emailNum <= 0) {
            throw new IllegalArgumentException("Email number must be positive");
        }
        if (emailNum > nameList.size()) {
            throw new IllegalArgumentException("Requested emailNum exceeds available names");
        }

        List<String> emails = new ArrayList<>(emailNum);

        for (int i = 0; i < emailNum; i++) {
            StringBuilder email = new StringBuilder();
            String name = nameToEmailUsername(nameList.get(i));
            int randomDomainIndex = random.nextInt(domains.size());
            String domain = domains.get(randomDomainIndex);
            email.append(name).append("@").append(domain);
            emails.add(email.toString());
        }

        return emails;
    }

    private @NotNull String nameToEmailUsername(@NotNull String name) {
        return name.toLowerCase()
                .replace(" ", ".")
                .replaceAll("[^a-zA-Z0-9._-]", "");
    }

    //Getters
    @Contract(value = " -> new", pure = true)
    public @NotNull List<String> getEmailList() {
        return this.emailList;
    }
}
