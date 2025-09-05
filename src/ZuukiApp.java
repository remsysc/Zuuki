import java.util.ArrayList;
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
 */
public class ZuukiApp {

    /** Maximum number of animals that can be stored in the inventory */
    private static final int kSize = 50;

    /** Scanner instance for reading user input throughout the application */
    private static final Scanner scan = new Scanner(System.in);

    /**
     * Main entry point of the application.
     * Initializes and runs the Zuuki animal inventory system.
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
     */
    private static void zuukiApp() {
        /* Initialize parallel arrays to store animal data
         * Using parallel arrays instead of objects for simplicity
         * All arrays maintain the same indexing system 
    	 * ArrayList is dynamic array, initial capacity will be kSize, 
    	 * it will automatically resize (usually 1.5x the old size) when needed */
        ArrayList<String> name = new ArrayList<>(kSize); 
        ArrayList<Integer> age = new ArrayList<>(kSize);
        ArrayList<String> species = new ArrayList<>(kSize);
        ArrayList<Integer> enclosure_number = new ArrayList<>(kSize);

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
             * Each case delegates to appropriate method */
            switch (choice) {
                case "1" -> createAnimal(
                    name,
                    age,
                    species,
                    enclosure_number
                );
                case "2" -> updateAnimal(
                    name,
                    age,
                    species,
                    enclosure_number
                );
                case "3" -> displayAnimal(
                    name,
                    age,
                    species,
                    enclosure_number
                );
                case "4" -> deleteAnimal(
                    name,
                    age,
                    species,
                    enclosure_number
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
     * in the parallel arrays.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     */
    private static void createAnimal(
    	ArrayList<String> name,
    	ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
        // Check if inventory has reached maximum capacity
        if (name.size() >= kSize) {
            System.out.println("Max limit has reached!");
            return;
        }

        System.out.println("Adding animal..\n.\n.");

        /* Collect animal information from user
         * Each input is validated through helper methods
         * Data is stored in the arrays*/
        name.add(readString("Name:"));
        age.add(readInt("Age: ", 0, 999));
        species.add(readString("Species:"));
        enclosure_number.add(readInt("Enclosure (0-4): ", 0, 4));

        System.out.println(".\n.\nEnd.");
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
     */
    private static void updateAnimal(
    	ArrayList<String> name,
    	ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
    	int size = name.size();
        // Early exit if no animals exist to update
        if (!isAnyAnimalDataExist(size)) return;

        /* Display all animals with their index numbers
         * This helps user identify which animal to update */
        for (int i = 0; i < name.size(); i++) {
            System.out.printf("%d. %s (%s)\n", i, name.get(i), species.get(i));
        }

        // Get user selection and update all attributes
        int index = readInt(
            "Which index to update (0-" + (size - 1) + "): ",
            0,
            size - 1
        );

        /* Prompt for new values for all attributes
         * Overwrites existing data at selected index */
        name.set(index, readString("New name: "));
        age.set(index, readInt("New age: ", 0, 999));
        species.set(index, readString("New species: "));
        enclosure_number.set(index, readInt("New enclosure (0-4): ", 0, 4));
    }

    /**
     * Deletes an animal from the inventory system.
     *
     * @param name Array storing animal names
     * @param age Array storing animal ages
     * @param species Array storing animal species
     * @param enclosure_number Array storing enclosure assignments
     */
    private static void deleteAnimal(
    	ArrayList<String> name,
    	ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
    	int size = name.size();
        if (!isAnyAnimalDataExist(size)) return;

        int index = readInt(
            "Index to delete (0 to " + (size - 1) + "): ",
            0,
            size - 1
        );

        name.remove(index);
        age.remove(index);
        species.remove(index);
        enclosure_number.remove(index);
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
     */
    private static void displayAnimal(
        ArrayList<String> name,
        ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
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
                    enclosure_number
                );
                case "2" -> searchAnimalBySpecies(
                    name,
                    age,
                    species,
                    enclosure_number
                );
                case "3" -> searchAnimalByEnclosure(
                    name,
                    age,
                    species,
                    enclosure_number
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
     */
    private static void searchAnimalByEnclosure(
    	ArrayList<String> name,
        ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
    	int size = name.size();
        if (!isAnyAnimalDataExist(size)) return;

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
                    enclosure_number
                );
                case "2" -> countAnimalByEnclosure(enclosure_number);
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
     */
    private static void displayAnimalsByEnclosure(
    	ArrayList<String> name,
    	ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
        int target = readInt("Enclosure (0-4): ", 0, 4);
        System.out.println("Displaying ..\n.\n.");

        /* Linear search through all animals
         * Display complete information for matches */
        for (int i = 0; i < enclosure_number.size(); i++) {
            if (enclosure_number.get(i) == target) {
                printAnimal(i, name, age, species, enclosure_number);
            }
        }
        System.out.println(".\n.\nEnd.");
    }

    /**
     * Counts animals of a specific species.
     *
     * Performs case-insensitive search through species array
     * and returns total count of matches.
     *
     * @param species Array storing animal species
     * @return Total count of animals with matching species
     */
    private static int countAnimalBySpecies(ArrayList<String> species) {
        String target = readString("Species to count: ");
        int total = 0;

        /* Case-insensitive comparison using equalsIgnoreCase()
         * This provides better user experience */
        for (int i = 0; i < species.size(); i++) {
            if (target.equalsIgnoreCase(species.get(i))) total++;
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
     * Counts animals in a specific enclosure.
     *
     * @param enclosure_number Array storing enclosure assignments
     * @return Total count of animals in specified enclosure
     */
    private static int countAnimalByEnclosure(ArrayList<Integer> enclosure_number) {
        int target = readInt("Enclosure (0-4): ", 0, 4);
        int total = 0;

        // Count animals with matching enclosure number
        for (int i = 0; i < enclosure_number.size(); i++) {
            if (enclosure_number.get(i) == target) total++;
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
     */
    private static void searchAnimalByName(
    	ArrayList<String> name,
        ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
    	int size = name.size();
        if (!isAnyAnimalDataExist(size)) return;

        String target = readString("Name to find: ");

        /* Linear search with early termination
         * Returns immediately when first match is found */
        for (int i = 0; i < size; i++) {
            if (name.get(i).equalsIgnoreCase(target)) {
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
     */
    private static void searchAnimalBySpecies(
    	ArrayList<String> name,
        ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
    	int size = name.size();
        if (!isAnyAnimalDataExist(size)) return;

        while (true) {
            displayAllSpecies(species); // Show available species
            String userInput = readString("1. Display\n2. Count\n3. Back");

            switch (userInput) {
                case "1" -> displayAnimalBySpecies(
                    name,
                    age,
                    species,
                    enclosure_number
                );
                case "2" -> countAnimalBySpecies(species);
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
     */
    private static void displayAnimalBySpecies(
    	ArrayList<String> name,
    	ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure_number
    ) {
        String target = readString("Species: ");
        System.out.println("Displaying..\n.\n.");

        /* Display all animals matching the specified species
         * No early termination - shows all matches */
        for (int i = 0; i < species.size(); i++) {
            if (species.get(i).equalsIgnoreCase(target)) {
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
     */
    private static void displayAllSpecies(ArrayList<String> species) {
    	ArrayList<String> uniqueSpecies = new ArrayList<>(); // Temporary array for unique species
        
        /* Algorithm to find unique species:
         * 1. Skip null/empty values
         * 2. Check if species already exists in result array
         * 3. Add to result if unique */
        StringBuilder result = new StringBuilder();
        for (String s : species) {
            if (s == null || s.isBlank()) continue; // Skip invalid entries

            // Only add if species doesn't already exist in results
            if (!uniqueSpecies.contains(s.toLowerCase())) {
            	uniqueSpecies.add(s.toLowerCase());
            	result.append(s).append(" | ");
            }
        }
        String uniqueSpeciesResult = result.toString();
        if(isAnyAnimalDataExist(uniqueSpeciesResult.length())) {
            System.out.println("Species Registered: \n"+uniqueSpeciesResult.replaceAll(" \\| $", ""));
        }
        
        System.out.println("\n.\n.");
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
        ArrayList<String> name,
        ArrayList<Integer> age,
        ArrayList<String> species,
        ArrayList<Integer> enclosure
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
            name.get(i),
            age.get(i),
            species.get(i),
            enclosure.get(i)
        );
    }
}
