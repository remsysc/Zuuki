import java.util.Scanner;

public class ZuukiApp {

    static final int kSize = 50; // constant
    static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //driver code
        //
        zuukiApp(); //run the application
    }

    static void zuukiApp() {
        //initialize size of arrays with kSize
        String[] name = new String[kSize];
        int[] age = new int[kSize];
        String[] species = new String[kSize];
        int[] enclosure_number = new int[kSize];
        int counter = 0; //tracks the available index

        System.out.println("Welcome to Zuuki <3.");
        System.out.println(
            "Your personal animal inventory app.\nPlease choose."
        );

        do {
            String choice = readString(
                """
                1. Add
                2. Update
                3. Display
                4. Delete
                5. Quit
                """
            );

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
                    return;
                }
                default -> System.out.println("Invalid Input.");
            }
        } while (true);
    }

    static int createAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //checks if theres available space
        if (counter >= kSize) {
            //early exit
            System.out.println("Max limit has reached!");
            return counter;
        }
        System.out.println("Adding animal..\n.\n.");

        while (true) {
            //bunch of user inputs
            name[counter] = readString("Name:");
            age[counter] = readInt("Age: ", 0, 999);
            species[counter] = readString("Species:");
            enclosure_number[counter] = readInt("Enclosure Number: ", 0, 4);

            counter++; //update counter
            break;
        }

        System.out.println(".\n.\nEnd.");

        return counter;
    }

    static void updateAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //early exit if no registered animal
        if (counter <= 0) {
            System.out.println(
                "No registered animal yet. Cannot proceed. Returning..\n.\n."
            );
            return;
        }

        int index = readInt(
            "Which index to update (0-" + (counter - 1) + "): ",
            0,
            counter - 1
        );
        name[index] = readString("New name: ");
        age[index] = readInt("New age: ", 0, 999);
        species[index] = readString("New species: ");
        enclosure_number[index] = readInt("New enclosure (0-4): ", 0, 4);
    }

    public static int deleteAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (counter <= 0) {
            System.out.println("Nothing to delete. Returning ..");
            return counter;
        }
        int index = readInt(
            "Index to delete (0 to " + (counter - 1) + "): ",
            0,
            counter - 1
        );
        //shift everything after index back one slot
        for (int i = index; i < counter - 1; i++) {
            name[i] = name[i + 1];
            age[i] = age[i + 1];
            species[i] = species[i + 1];
            enclosure_number[i] = enclosure_number[i + 1];
        }

        return counter - 1;
    }

    public static void displayAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        do {
            System.out.println("Diplay options:");

            String userInput = readString(
                "1. Name\n2. Species\n3. Enclosure\n4.Back"
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
                    return;
                }
                default -> System.out.println("Invalid Input.");
            }
        } while (true);
    }

    public static void searchAnimalByEnclosure(
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

    private static void displayAnimalsByEnclosure(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        int target = readInt("Enclosure (0-4): ", 0, 4);
        System.out.println("Displaying ..\n.\n.");

        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == target) {
                System.out.printf(
                    """
                    Enclosure: %d
                    Name: %s
                    Age: %d
                    Species: %s
                    """,
                    target,
                    name[i],
                    age[i],
                    species[i]
                );
            }
        }

        System.out.println(".\n.\nEnd.");
    }

    //method overloading
    //count animals by species
    public static int countAnimals(String[] species, int counter) {
        String target = readString("Species to count: ");
        int total = 0;

        for (int i = 0; i < counter; i++) {
            if (target.equalsIgnoreCase(species[i])) total++;
        }
        if (total <= 0) {
            System.out.println("No animals were found.");
        } else System.out.println(
            "A total of " +
            total +
            " animal/s were found with species of " +
            target +
            "."
        );
        return total;
    }

    public static int countAnimals(int[] enclosure_number, int counter) {
        //get user inputs
        int target = readInt("Enclosure (0-4): ", 0, 4);

        int total = 0;

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

    public static void searchAnimalByName(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (!isAnyAnimalDataExist(counter)) return;

        String target = readString("Name to find: ");
        for (int i = 0; i < counter; i++) {
            if (name[i].equalsIgnoreCase(target)) {
                System.out.printf(
                    """
                    Found at index %d:
                    Name: %s
                    Age: %d
                    Species: %s
                    Enclosure %d
                    """,
                    i,
                    name[i],
                    age[i],
                    species[i],
                    enclosure_number[i]
                );
                return;
            }
        }
        System.out.println(target + " not found.");
    }

    public static void searchAnimalBySpecies(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        if (!isAnyAnimalDataExist(counter)) return;

        do {
            displayAllSpecies(species, counter);

            String userInput = readString("1.Display\n2. Count");

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
        } while (true);
    }

    private static void displayAnimalBySpecies(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //get the user input

        String target = readString("Species: ");

        //display all animals of that scpecies
        //
        System.out.println("Displaying..\n.\n.");
        for (int i = 0; i < counter; i++) {
            if (species[i].equalsIgnoreCase(target)) {
                System.out.printf(
                    """
                    Species: %s
                    Name: %s
                    Age: %d
                    Enclosure: %d
                    """,
                    target,
                    name[i],
                    age[i],
                    enclosure_number[i]
                );
            }
        }
        System.out.println("\n.\n.\nEnd.");
    }

    private static void displayAllSpecies(String[] species, int counter) {
        if (counter <= 0) System.out.println("No species found.");

        String[] res = new String[counter];
        int count = 0;
        for (String s : species) {
            //checks if already exist
            if (s == null || s.isBlank()) continue; // skips null /empty values
            if (!isSpeciesAlreadyExist(res, s, count)) {
                res[count] = s;
                count++;
            }
        }

        System.out.println("Species Registered: ");
        for (var s : res) {
            if (s == null) continue;
            System.out.print(s + " | ");
        }
        System.out.println("\n.\n.");
    }

    private static boolean isSpeciesAlreadyExist(
        String[] result,
        String str,
        int counter
    ) {
        for (int i = 0; i < counter; i++) {
            if (
                result[i] != null && result[i].equalsIgnoreCase(str)
            ) return true;
        }

        return false;
    }

    public static boolean isAnyAnimalDataExist(int counter) {
        if (counter <= 0) {
            System.out.println(
                "[No registered animals yet, please consider adding one first]"
            );
            return false;
        }
        return true;
    }

    static String readString(String prompt) {
        System.out.println(prompt);
        System.out.print(">> ");

        return scan.nextLine().trim();
    }

    static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            System.out.print(">> ");
            try {
                int userInput = Integer.parseInt(scan.nextLine());

                if (userInput < min || userInput > max) {
                    System.out.printf(
                        "Enter a value between %d and %d.%n",
                        min,
                        max
                    );
                    continue;
                }
                return userInput;
            } catch (NumberFormatException e) {
                System.out.println("Not a number. Try Again.");
            }
        }
    }
}
