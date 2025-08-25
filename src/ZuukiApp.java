import java.util.Scanner;

public class ZuukiApp {

    public static void main(String[] args) {
        //driver code
        //
        zuukiApp(); //run the application
    }

    public static void zuukiApp() {
        //TODO: CREATE A FUNCTIONAL UI HERE, THAT THROWS AN EXCEPTION WHENEVER NECESSARRY

        final int kSize = 50; // constant

        //initialize size of arrays with kSize
        String[] name = new String[kSize];
        int[] age = new int[kSize];
        String[] species = new String[kSize];
        int[] enclosure_number = new int[kSize];

        int counter = 0; //tracks the available index

        //updates counter
        counter = createAnimal(
            name,
            age,
            species,
            enclosure_number,
            counter,
            kSize
        ); // PASS BY VALUE of the REFERENCE
        //  updateAnimal(name, age, species, enclosure_number, counter, kSize);
        //deleteAnimal(name, age, species, enclosure_number, counter, kSize);
        System.out.println(counter);
        displayAnimal(name, age, species, enclosure_number, counter);
    }

    public static int createAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter,
        int kSize
    ) {
        //checks if theres available space
        if (counter >= kSize) return 0; //early exit

        Scanner scan = new Scanner(System.in);
        boolean isCleanInputs = false; //flag if clean inputs

        do {
            try {
                //bunch of user inputs
                System.out.print("Name: ");
                name[counter] = scan.nextLine();

                System.out.print("Age: ");
                age[counter] = Integer.parseInt(scan.nextLine());

                System.out.print("Species: ");
                species[counter] = scan.nextLine();

                System.out.print("Enclosure number: ");
                enclosure_number[counter] = Integer.parseInt(scan.nextLine());

                counter++; //update counter
                isCleanInputs = true; //break loop
            } catch (NumberFormatException e) {
                //loop back if input aint clean
                System.out.println("Invalid Input: " + e.getMessage()); //echo the error
                System.out.println("Please try again.");
            }
        } while (!isCleanInputs);

        //scan.close(); //close the scanner
        return counter;
    }

    public static void updateAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter,
        int kSize
    ) {
        //Ask the index of availabe animals

        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        do {
            System.out.println("Available index 0 - " + (counter - 1));
            System.out.println("What animal you want to update?");

            try {
                int userInput = Integer.parseInt(scan.nextLine());

                // checks if input is valid
                if (userInput > counter - 1) {
                    System.out.println("Invalid Range.");
                    continue;
                } else createAnimal(
                    name,
                    age,
                    species,
                    enclosure_number,
                    userInput,
                    kSize
                );

                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input: " + e.getMessage());
            }
        } while (!isValid);
    }

    public static void deleteAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter,
        int kSizes
    ) {
        //TODO: SHIFT THE "DELETED" to the end of array

        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        do {
            try {
                System.out.print("Inde of animal you want to delete: ");
                int userInput = Integer.parseInt(scan.nextLine());

                //checks the validity
                if (userInput > counter - 1) {
                    System.out.println("Invalid Index");
                } else {
                    name[userInput] = "[N/A]";
                    age[userInput] = 0;
                    species[userInput] = "[N/A]";
                    enclosure_number[userInput] = 0;
                }
                isValid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            }
        } while (!isValid);
    }

    public static void displayAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println(
                "1. Search animal by name\n2. Count Animals by species\n3. Display animal by specific enclosure"
            );
            int userInput = getUserInput(0);
            switch (userInput) {
                case 1:
                    searchAnimalByName(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case 2:
                    //searchBySpecies
                    // count and display via group
                    searchAnimalBySpecies(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case 3:
                    displayAnimalsByEnclosureNumber(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                default:
                    System.out.println("Invalid Input.");
            }
            String tmp = userPrompt();
            if (tmp.equalsIgnoreCase("c")) continue;
            if (tmp.equalsIgnoreCase("b")) return;
        } while (true);
    }

    public static void displayAnimalsByEnclosureNumber(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        // display the range of enclosure
        // display count of animals per enclosure and display animals per enclosure
        do {
            System.out.println("Enclosure Number: 0 -- 4");

            System.out.println(
                "1. Display animals by enclosure #.\n2. Count animals inside a specific enclosure number."
            );

            int userInput = getUserInput(0);

            switch (userInput) {
                case 1:
                    displayAnimalsByEnclosure(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case 2:
                    countAnimals(enclosure_number, counter);
                    break;
                default:
                    System.out.println("Invalid Input.");
                    break;
            }

            String tmp = userPrompt();
            if (tmp.equalsIgnoreCase("c")) continue;
            if (tmp.equalsIgnoreCase("b")) return;
        } while (true);
    }

    private static void displayAnimalsByEnclosure(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        int count = 1;
        int userInput = getEnclosureInput();
        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == userInput) {
                displayAnimal(i, count, name, age, species);
                count++;
            }
        }
    }

    private static int getEnclosureInput() {
        int userInput = -1;

        while (true) {
            System.out.println("Enter Enclosure #: ");
            userInput = getUserInput(userInput);

            if (userInput < 0 || userInput > 4) {
                System.out.println("Invalid Range.");
                continue;
            }
            break;
        }
        return userInput;
    }

    public static void displayAnimal(
        int index,
        int count,
        String[] name,
        int[] age,
        String[] species
    ) {
        System.out.println(count + ".");
        System.out.println("Name: " + name[index]);
        System.out.println("Age: " + age[index]);
        System.out.println("Species: " + species[index] + "- - - - - - -\n");
    }

    //method overloading
    public static int countAnimals(String[] species, int counter) {
        //get user inputs
        System.out.println("Enter the specie: ");
        String userInput = getUserInput();

        int total = 0;

        for (int i = 0; i < counter; i++) {
            if (userInput.equalsIgnoreCase(species[i])) total++;
        }
        if (total <= 0) {
            System.out.println("No animals were found.");
        } else System.out.println(
            "A total of " + total + " animals were found."
        );
        return total;
    }

    public static int countAnimals(int[] enclosure_number, int counter) {
        //get user inputs
        int userInput = getEnclosureInput();

        int total = 0;

        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == userInput) total++;
        }
        System.out.println("A total of " + total + " animals were found.");
        return total;
    }

    public static void searchAnimalByName(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //get user inputs
        //
        //
        boolean isValid = true;
        do {
            System.out.println("Enter the name of animal: ");
            String userInput = getUserInput();

            int index = isNameValid(userInput, name, counter);
            if (index < 0) {
                String tmp = userPrompt();

                if (tmp.equalsIgnoreCase("c")) continue;
                if (tmp.equalsIgnoreCase("b")) return;
            }

            //display all information regarding the found userInput

            System.out.println("Name: " + name[index]);
            System.out.println("Age: " + age[index]);
            System.out.println("Species: " + species[index]);
            System.out.println("Enclosure Number: " + enclosure_number[index]);

            isValid = false;
        } while (isValid);
    }

    public static void searchAnimalBySpecies(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        // display all availabe species only
        //
        // display specific species
        // ccount animals belong to specific species
        //
        displayAllSpecies(species, counter);

        //menu
        //
        //
        boolean isValid = true;
        do {
            System.out.println("Available Species: ");
            displayAllSpecies(species, counter);
            System.out.println(
                "1. Display animals By specific species\n2.Count Animals by Specific species"
            );

            int userInput = getUserInput(0);

            switch (userInput) {
                case 1:
                    displayAnimalBySpecies(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case 2:
                    countAnimals(species, counter);
                    break;
                default:
                    System.out.println("Invalid Input");
            }

            String tmp = userPrompt();
            if (tmp.equalsIgnoreCase("c")) continue;
            if (tmp.equalsIgnoreCase("b")) return;
        } while (isValid);
    }

    private static void displayAnimalBySpecies(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //get the user input
        System.out.println("Species: ");
        String userInput = getUserInput();

        //display all animals of that scpecies
        //
        System.out.println("Displaying..\n.\n.");
        for (int i = 0; i < counter; i++) {
            if (species[i].equalsIgnoreCase(userInput)) {
                //display all details if true
                System.out.println("Name: " + name[i]);
                System.out.println("Age: " + age[i]);
                System.out.println("Enclosure Number: " + enclosure_number[i]);
            }
        }
        System.out.println("\n.\n.\nEnd.");
    }

    private static void displayAllSpecies(String[] species, int counter) {
        if (counter <= 0) System.out.println("No registered animal.");

        //counter - 1 is the actual number of registered animals
        //
        String[] res = new String[counter];
        int count = 0;
        for (String s : species) {
            //checks if already exist
            if (s == null || s.isBlank()) continue; // skips null /empty values
            if (!isAlreadyExist(res, s, count)) {
                res[count] = s;
                count++;
            }
        }

        System.out.println("Species Registered: ");
        for (var s : res) {
            if (s == null) continue;
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private static boolean isAlreadyExist(
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

    public static int isNameValid(
        String userInput,
        String[] name,
        int counter
    ) {
        for (int i = 0; i < counter; i++) {
            //linear search
            if (userInput.equalsIgnoreCase(name[i])) return i;
        }
        System.out.println(userInput + " not found.");
        return -1;
    }

    public static String getUserInput() {
        Scanner scan = new Scanner(System.in);
        System.out.print(">> ");
        return scan.nextLine();
    }

    public static int getUserInput(int userInput) {
        Scanner scan = new Scanner(System.in);
        System.out.print(">> ");
        try {
            userInput = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {}

        return userInput;
    }

    public static String userPrompt() {
        while (true) {
            System.out.println(
                "Press 'c' to try again and 'q' to quit or 'b' to back "
            );
            String tmp = getUserInput();

            if (tmp.length() > 1) {
                System.out.println("Invalid Input. Try again.");
                continue;
            } else if (tmp.equalsIgnoreCase("c")) return "c";
            else if (tmp.equalsIgnoreCase("b")) return "b";
            else if (tmp.equalsIgnoreCase("q")) {
                System.out.println("Thanks for using Zuuki <3..");
                System.exit(0);
            } else {
                System.out.println("Invalid Input");
            }
        }
    }
}
