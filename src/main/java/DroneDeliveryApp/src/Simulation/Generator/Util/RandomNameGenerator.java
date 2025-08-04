package DroneDeliveryApp.src.Simulation.Generator.Util;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * A utility class for generating random names composed of a first name and a last name.
 * The names are sourced from predefined arrays of possible first names and last names.
 */
public final class RandomNameGenerator {
    private final Random random;
    private final String[] firstNames;
    private final String[] lastNames;
    private final String[] nameList;
    private static final String[] DEFAULT_FIRST_NAMES = {
            "Luna", "Maxwell", "Astra", "Kai", "Nova", "Orion", "Aria", "Leo",
            "Aurora", "Sage", "Caspian", "Lyra", "Vega", "Celeste",
            "Cosmos", "Stella", "Apollo", "Carina", "Thea", "Zephyr", "Perseus",
            "Astrid", "Cassidy", "Echo", "Jupiter", "Mars", "Mercury", "Neptune",
            "Solaris", "Terra", "Venus", "Zeus", "Andromeda", "Callisto", "Helios", "Io"
    };
    private static final String[] DEFAULT_LAST_NAMES = {
            "Storm", "Night", "Iron", "Blaze", "Shadow", "Frost", "Sky", "Drake",
            "Steel", "Flame", "Thunder", "Wolf", "Hawk", "Stone", "Crest", "Vale",
            "Blade", "Dawn", "Dusk", "Fang", "Glacier", "Phoenix", "River", "Slate",
            "Spark", "Star", "Swift", "Tide", "Wing", "Winter", "Ash", "Cloud",
            "Ember", "Forest", "Moon", "Rain", "Snow", "Wind", "Claw", "Mountain"
    };

    /**
     * Constructs a RandomNameGenerator object that generates a specified number
     * of random names using the provided arrays of first names and last names.
     * If custom arrays are not provided, default arrays of first and last names are used.
     *
     * @param nameNum the number of names to generate; must be a positive integer
     * @param customFirstNames an optional array of custom first names; if null or empty, default first names are used
     * @param customLastNames an optional array of custom last names; if null or empty, default last names are used
     * @param random the Random instance used for generating random indices for name selection
     * @throws IllegalArgumentException if the number of names is non-positive
     *                                  or exceeds the total possible unique combinations
     */
    public RandomNameGenerator(int nameNum, String[] customFirstNames, String[] customLastNames, Random random) {
        this.random = random;
        this.firstNames = customFirstNames != null && customFirstNames.length > 0 ? customFirstNames : DEFAULT_FIRST_NAMES;
        this.lastNames = customLastNames != null && customLastNames.length > 0 ? customLastNames : DEFAULT_LAST_NAMES;
        this.nameList = generateName(nameNum);
    }


    /**
     * Generates an array of unique names composed of a first name and a last name.
     * The names are randomly selected from predefined arrays of first and last names.
     *
     * @param nameNum the number of unique names to generate; must be a positive integer
     * @return an array of unique names in the format "FirstName LastName"
     * @throws IllegalArgumentException if the number of names is non-positive
     *                                  or exceeds the total possible unique combinations
     */
    public @NotNull String[] generateName(int nameNum) {
        int firstNamesLength = this.firstNames.length;
        int lastNamesLength = this.lastNames.length;

        if (nameNum <= 0) {
            throw new IllegalArgumentException("Number of names must be positive");
        }
        if (nameNum > firstNamesLength * lastNamesLength) {
            throw new IllegalArgumentException("Cannot generate more unique names than possible combinations");
        }

        Set<String> uniqueNames = new HashSet<>();
        while (uniqueNames.size() < nameNum) {
            int firstIndex = random.nextInt(firstNamesLength);
            int lastIndex = random.nextInt(lastNamesLength);
            uniqueNames.add(this.firstNames[firstIndex] + " " + this.lastNames[lastIndex]);
        }

        return uniqueNames.toArray(new String[0]);
    }

    // Getters
    public String[] getNameList() {
        return this.nameList.clone();
    }
}
