import java.util.Scanner;

/**
 * ZuukiApp - Personal Animal Inventory Management System
 *
 * This application provides a console-based interface for managing
 * an animal inventory with basic CRUD operations (Create, Read, Update, Delete).
 * Each animal has attributes: name, age, species, and enclosure number.
 *
 * Features:
 * - Add new animals to inventory
 * - Update existing animal information
 * - Display animals by various criteria (name, species, enclosure)
 * - Delete animals from inventory
 * - Count animals by species or enclosure
 *
 * @author Your Name
 * @version 1.0
 */
public class ZuukiApp {

    /** Maximum number of animals that can be stored in the inventory */
    private static final int kSize = 50;

    /** Scanner instance for reading user input throughout the application */
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Main entry point of the application.
     * Initializes and runs the Zuuki animal inventory system.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        zuukiApp(); // Launch the main application
    }

    /**
     * Core application loop that handles the main menu and user interactions.
     *
     * Initializes parallel arrays to store animal data:
     * - name: Animal names
     * - age: Animal ages
     * - species: Animal species
     * - enclosure_number: Enclosure assignments (0-4)
     *
     * Uses a counter variable to track the next available index position
     * and maintain the current number of animals in the system.
     */
    private static void zuukiApp() {
        /* Initialize parallel arrays to store animal data
         * Using parallel arrays instead of objects for simplicity
         * All arrays maintain the same indexing system */
        String[] name = new String[kSize];
        int[] age = new int[kSize];
        String[] species = new String[kSize];
        int[] enclosure_number = new int[kSize];

        int counter = 0; // Tracks next available index and total animal count

        System.out.println("Welcome to Zuuki <3.");
        System.out.println(
            "Your personal animal inventory app.\nPlease choose."
        );

        // Main application loop - continues until user chooses to quit
        while (true) {
            String choice = readString(
                """
                1. Add
                2. Update
                3. Display
                4. Delete
                5. Quit
                """
            );

            /* Switch statement handles menu navigation
             * Each case delegates to appropriate method
             * Counter is updated when animals are added/removed */
            switch (choice) {
                case "1" -> counter = createAnimal(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "2" -> updateAnimal(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "3" -> displayAnimal(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "4" -> counter = deleteAnimal(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "5" -> {
                    System.out.println("Thanks for using Zuuki <3.");
                    return; // Exit application
                }
                default -> System.out.println("Invalid Input.");
            }
        }
    }

    /**
     * Creates a new animal entry in the inventory system.
     *
     * Validates that there is available space before allowing creation.
     * Prompts user for all required animal information and stores it
     * in the parallel arrays at the current counter position.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals and next available index
     * @return Updated counter value after successful creation
     */
    private static int createAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        // Check if inventory has reached maximum capacity
        if (counter >= kSize) {
            System.out.println("Max limit has reached!");
            return counter; // Return unchanged counter - no animal added
        }

        System.out.println("Adding animal..\n.\n.");

        /* Collect animal information from user
         * Each input is validated through helper methods
         * Data is stored at the current counter index */
        name[counter] = readString("Name:");
        age[counter] = readInt("Age: ", 0, 999);
        species[counter] = readString("Species:");
        enclosure_number[counter] = readInt("Enclosure (0-4): ", 0, 4);

        counter++; // Increment to next available position
        System.out.println(".\n.\nEnd.");
        return counter; // Return updated counter
    }

    /**
     * Updates an existing animal's information in the inventory.
     *
     * Displays all current animals with their index numbers,
     * allows user to select which animal to update, then
     * prompts for new values for all attributes.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void updateAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        // Early exit if no animals exist to update
        if (!isAnyAnimalDataExist(counter)) return;

        /* Display all animals with their index numbers
         * This helps user identify which animal to update */
        for (int i = 0; i < counter; i++) {
            System.out.printf("%d. %s (%s)\n", i, name[i], species[i]);
        }

        // Get user selection and update all attributes
        int index = readInt(
            "Which index to update (0-" + (counter - 1) + "): ",
            0,
            counter - 1
        );

        /* Prompt for new values for all attributes
         * Overwrites existing data at selected index */
        name[index] = readString("New name: ");
        age[index] = readInt("New age: ", 0, 999);
        species[index] = readString("New species: ");
        enclosure_number[index] = readInt("New enclosure (0-4): ", 0, 4);
    }

    /**
     * Deletes an animal from the inventory system.
     *
     * Uses array shifting technique to maintain data integrity.
     * When an animal is deleted, all animals after that position
     * are shifted one position to the left to eliminate gaps.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     * @return Updated counter value after deletion
     */
    private static int deleteAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (!isAnyAnimalDataExist(counter)) return counter;

        int index = readInt(
            "Index to delete (0 to " + (counter - 1) + "): ",
            0,
            counter - 1
        );

        /* Array shifting algorithm to remove gaps
         * Starting from deletion point, copy each element
         * from the next position to current position
         * This maintains data continuity without gaps */
        for (int i = index; i < counter - 1; i++) {
            name[i] = name[i + 1];
            age[i] = age[i + 1];
            species[i] = species[i + 1];
            enclosure_number[i] = enclosure_number[i + 1];
        }

        return counter - 1; // Reduce counter as one animal was removed
    }

    /**
     * Main display menu handler that provides different viewing options.
     *
     * Offers three ways to view animal data:
     * 1. Search by name (exact match)
     * 2. Browse by species (with counting)
     * 3. Browse by enclosure (with counting)
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void displayAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        // Display options loop until user chooses to return
        while (true) {
            System.out.println("Diplay options:");
            String userInput = readString(
                "1. Name\n2. Species\n3. Enclosure\n4. Back"
            );

            switch (userInput) {
                case "1" -> searchAnimalByName(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "2" -> searchAnimalBySpecies(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "3" -> searchAnimalByEnclosure(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "4" -> {
                    System.out.println("Returning..");
                    return; // Exit display menu
                }
                default -> System.out.println("Invalid Input.");
            }
        }
    }

    /**
     * Handles enclosure-based animal operations.
     *
     * Provides two main functions:
     * 1. Display all animals in a specific enclosure
     * 2. Count animals in a specific enclosure
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void searchAnimalByEnclosure(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (!isAnyAnimalDataExist(counter)) return;

        while (true) {
            String choice = readString(
                """
                Enclosure (0-4):
                1. Display
                2. Count
                3. Back
                """
            );

            switch (choice) {
                case "1" -> displayAnimalsByEnclosure(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "2" -> countAnimals(enclosure_number, counter);
                case "3" -> {
                    System.out.println("Returning..");
                    return;
                }
                default -> System.out.println("Invalid Input.");
            }
        }
    }

    /**
     * Displays all animals located in a specific enclosure.
     *
     * Prompts user for target enclosure number, then iterates
     * through all animals to find and display matches.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void displayAnimalsByEnclosure(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        int target = readInt("Enclosure (0-4): ", 0, 4);
        System.out.println("Displaying ..\n.\n.");

        /* Linear search through all animals
         * Display complete information for matches */
        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == target) {
                printAnimal(i, name, age, species, enclosure_number);
            }
        }
        System.out.println(".\n.\nEnd.");
    }

    /**
     * Counts animals of a specific species (method overloading example).
     *
     * Performs case-insensitive search through species array
     * and returns total count of matches.
     *
     * @param species Array storing animal species
     * @param counter Current number of animals in inventory
     * @return Total count of animals with matching species
     */
    private static int countAnimals(String[] species, int counter) {
        String target = readString("Species to count: ");
        int total = 0;

        /* Case-insensitive comparison using equalsIgnoreCase()
         * This provides better user experience */
        for (int i = 0; i < counter; i++) {
            if (target.equalsIgnoreCase(species[i])) total++;
        }

        if (total <= 0) {
            System.out.println("No animals were found.");
        } else {
            System.out.println(
                "A total of " +
                total +
                " animal/s were found with species of " +
                target +
                "."
            );
        }
        return total;
    }

    /**
     * Counts animals in a specific enclosure (method overloading example).
     *
     * Demonstrates method overloading - same method name but different
     * parameter types (int[] vs String[]).
     *
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     * @return Total count of animals in specified enclosure
     */
    private static int countAnimals(int[] enclosure_number, int counter) {
        int target = readInt("Enclosure (0-4): ", 0, 4);
        int total = 0;

        // Count animals with matching enclosure number
        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == target) total++;
        }

        System.out.println(
            "A total of " +
            total +
            " animals were found at enclosure number " +
            target +
            "."
        );
        return total;
    }

    /**
     * Searches for and displays a specific animal by name.
     *
     * Performs exact name matching (case-insensitive) and displays
     * the first match found. Returns immediately after finding match.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void searchAnimalByName(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (!isAnyAnimalDataExist(counter)) return;

        String target = readString("Name to find: ");

        /* Linear search with early termination
         * Returns immediately when first match is found */
        for (int i = 0; i < counter; i++) {
            if (name[i].equalsIgnoreCase(target)) {
                printAnimal(i, name, age, species, enclosure_number);
                return; // Exit after finding first match
            }
        }
        System.out.println(target + " not found.");
    }

    /**
     * Handles species-based animal operations with submenu.
     *
     * First displays all unique species currently in the system,
     * then provides options to display animals by species or
     * count animals by species.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void searchAnimalBySpecies(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (!isAnyAnimalDataExist(counter)) return;

        while (true) {
            displayAllSpecies(species, counter); // Show available species
            String userInput = readString("1. Display\n2. Count 3. Back");

            switch (userInput) {
                case "1" -> displayAnimalBySpecies(
                    name,
                    age,
                    species,
                    enclosure_number,
                    counter
                );
                case "2" -> countAnimals(species, counter);
                case "3" -> {
                    System.out.println("Returning..");
                    return;
                }
                default -> System.out.println("Invalid Input");
            }
        }
    }

    /**
     * Displays all animals of a specific species.
     *
     * Prompts user for species name and displays all matching animals.
     * Uses case-insensitive comparison for better user experience.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     * @param counter Current number of animals in inventory
     */
    private static void displayAnimalBySpecies(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        String target = readString("Species: ");
        System.out.println("Displaying..\n.\n.");

        /* Display all animals matching the specified species
         * No early termination - shows all matches */
        for (int i = 0; i < counter; i++) {
            if (species[i].equalsIgnoreCase(target)) {
                printAnimal(i, name, age, species, enclosure_number);
            }
        }
        System.out.println("\n.\n.\nEnd.");
    }

    /**
     * Displays all unique species currently registered in the system.
     *
     * Creates a temporary array to store unique species names,
     * then displays them in a user-friendly format. Handles
     * null values and duplicates appropriately.
     *
     * @param species Array storing animal species
     * @param counter Current number of animals in inventory
     */
    private static void displayAllSpecies(String[] species, int counter) {
        String[] res = new String[counter]; // Temporary array for unique species
        int count = 0;

        /* Algorithm to find unique species:
         * 1. Skip null/empty values
         * 2. Check if species already exists in result array
         * 3. Add to result if unique */
        for (String s : species) {
            if (s == null || s.isBlank()) continue; // Skip invalid entries

            // Only add if species doesn't already exist in results
            if (!isSpeciesAlreadyExist(res, s, count)) {
                res[count] = s;
                count++;
            }
        }

        System.out.println("Species Registered: ");

        // Display all unique species found
        for (var s : res) {
            if (s == null) continue; // Skip empty slots
            System.out.print(s + " | ");
        }
        System.out.println("\n.\n.");
    }

    /**
     * Helper method to check if a species already exists in the result array.
     *
     * Used by displayAllSpecies() to prevent duplicate entries.
     * Performs case-insensitive comparison.
     *
     * @param result Array of unique species found so far
     * @param str Species name to check
     * @param counter Number of species currently in result array
     * @return true if species already exists, false otherwise
     */
    private static boolean isSpeciesAlreadyExist(
        String[] result,
        String str,
        int counter
    ) {
        for (int i = 0; i < counter; i++) {
            if (result[i] != null && result[i].equalsIgnoreCase(str)) {
                return true; // Species found in results
            }
        }
        return false; // Species not found - safe to add
    }

    /**
     * Validates if any animal data exists in the system.
     *
     * Common validation method used throughout the application
     * to prevent operations on empty inventory.
     *
     * @param counter Current number of animals in inventory
     * @return true if animals exist, false if inventory is empty
     */
    private static boolean isAnyAnimalDataExist(int counter) {
        if (counter <= 0) {
            System.out.println(
                "[No registered animals yet, please consider adding one first]"
            );
            return false;
        }
        return true;
    }

    /**
     * Utility method for reading string input from user.
     *
     * Displays prompt, reads user input, and trims whitespace.
     * Provides consistent input handling across the application.
     *
     * @param prompt Message to display to user
     * @return User's input with leading/trailing whitespace removed
     */
    private static String readString(String prompt) {
        System.out.println(prompt);
        System.out.print(">> ");
        return scan.nextLine().trim(); // Remove leading/trailing spaces
    }

    /**
     * Utility method for reading and validating integer input.
     *
     * Provides input validation with range checking and error handling.
     * Continues prompting until valid input is received.
     *
     * @param prompt Message to display to user
     * @param min Minimum acceptable value (inclusive)
     * @param max Maximum acceptable value (inclusive)
     * @return Valid integer within specified range
     */
    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            System.out.print(">> ");
            try {
                int userInput = Integer.parseInt(scan.nextLine());

                // Validate input is within acceptable range
                if (userInput < min || userInput > max) {
                    System.out.printf(
                        "Enter a value between %d and %d.%n",
                        min,
                        max
                    );
                    continue; // Re-prompt for valid input
                }
                return userInput; // Valid input received
            } catch (NumberFormatException e) {
                System.out.println("Not a number. Try Again.");
                // Loop continues to re-prompt user
            }
        }
    }

    /**
     * Utility method for formatting and displaying animal information.
     *
     * Provides consistent formatting for animal data display
     * across different parts of the application.
     *
     * @param i Index of the animal in the arrays
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure Array storing enclosure assignments
     */
    private static void printAnimal(
        int i,
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure
    ) {
        System.out.printf(
            """
            Index: %d
            Name: %s
            Age: %d
            Species: %s
            Enclosure: %d
            ------------------
            """,
            i,
            name[i],
            age[i],
            species[i],
            enclosure[i]
        );
    }
}
