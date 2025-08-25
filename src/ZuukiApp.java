import java.util.Scanner;

public class ZuukiApp {

    static final int kSize = 50; // constant

    public static void main(String[] args) {
        //driver code
        //
        zuukiApp(); //run the application
    }

    public static void zuukiApp() {
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
            System.out.println("1. Add\n2. Update\n3. Display\n4. Delete");
            String userInput = userPrompt();

            switch (userInput) {
                case "1":
                    counter = createAnimal(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter

                    );
                    break;
                case "2":
                    updateAnimal(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter

                    );
                    break;
                case "3":
                    displayAnimal(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case "4":
                    deleteAnimal(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter,

                    );
                case "c":
                    continue;
                case "b":
                    System.out.println("Thanks for using Zuuki <3.");
                    System.exit(0);
                default:
                    System.out.println("Invalid Input.");
            }
        } while (true);
    }

    public static int createAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //checks if theres available space
        if (counter >= kSize) return 0; //early exit

        System.out.println("Doing something..\n.\n.");

        Scanner scan = new Scanner(System.in);
        boolean isCleanInputs = false; //flag if clean inputs

        do {
            //bunch of user inputs
            System.out.println("Name: ");
            name[counter] = getUserInput();

            System.out.println("Age: ");
            age[counter] = getUserInput(0);

            System.out.println("Species: ");
            species[counter] = getUserInput();

            enclosure_number[counter] = getEnclosureInput();

            counter++; //update counter
            isCleanInputs = true; //break loop
        } while (!isCleanInputs);

        System.out.println(".\n.\nEnd.");

        return counter;
    }

    public static void updateAnimal(
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

        boolean isValid = false;
        do {
            System.out.println(
                "Available registered animal from 1 - " + (counter)
            );
            System.out.println("Which animal you want to update?");

            int userInput = getUserInput(0);

            // checks if input is valid
            if (userInput > counter || userInput <= 0) {
                System.out.println("Invalid Range.");
                continue;
            } else createAnimal(
                name,
                age,
                species,
                enclosure_number,
                userInput - 1,
                kSize
            );

            isValid = true;
        } while (!isValid);
    }

    public static void deleteAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //TODO: SHIFT THE "DELETED" to the end of array

        Scanner scan = new Scanner(System.in);
        boolean isValid = false;
        do {
            try {
                System.out.println("Animal index: ");
                int userInput = getUserInput(0);

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
            System.out.println("Diplay options:");
            System.out.println("1. Name\n2. Species\n3. Enclosure");

            String userInput = userPrompt();
            switch (userInput) {
                case "1":
                    searchAnimalByName(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case "2":
                    searchAnimalBySpecies(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case "3":
                    searchAnimalByEnclosure(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case "c":
                    continue;
                case "b":
                    System.out.println("Returning..");
                    return;
                default:
                    System.out.println("Invalid Input.");
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
        do {
            System.out.println("Enclosure Number: 0 -- 4");

            System.out.println("1. Display\n2. Count");

            String userInput = userPrompt();

            switch (userInput) {
                case "1":
                    displayAnimalsByEnclosure(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case "2":
                    countAnimals(enclosure_number, counter);
                    break;
                case "c":
                    continue;
                case "b":
                    System.out.println("Returning..");
                    return;
                default:
                    System.out.println("Invalid Input.");
                    break;
            }
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

        System.out.println("Displaying ..\n.\n.");

        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == userInput) {
                displayAnimal(i, count, name, age, species);
                count++;
            }
        }

        System.out.println(".\n.\nEnd.");
    }

    private static int getEnclosureInput() {
        int userInput = -1;

        while (true) {
            System.out.println("Enter Enclosure (0 - 4): ");
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
        System.out.println("Species: " + species[index]);
    }

    //method overloading
    public static int countAnimals(String[] species, int counter) {
        System.out.println("Enter the specie: ");
        String userInput = getUserInput();

        int total = 0;

        for (int i = 0; i < counter; i++) {
            if (userInput.equalsIgnoreCase(species[i])) total++;
        }
        if (total <= 0) {
            System.out.println("No animals were found.");
        } else System.out.println(
            "A total of " +
            total +
            " animal/s were found with species of " +
            userInput +
            "."
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
        System.out.println(
            "A total of " +
            total +
            " animals were found at enclosure number " +
            userInput +
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

        do {
            System.out.println("Enter the name of animal: ");
            String userInput = getUserInput();

            System.out.println("Searching the archives..\n.\n.");

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

            break;
        } while (true);

        System.out.println(".\n.\nEnd.");
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
            System.out.println("1. Display\n2. Count");

            String userInput = userPrompt();

            switch (userInput) {
                case "1":
                    displayAnimalBySpecies(
                        name,
                        age,
                        species,
                        enclosure_number,
                        counter
                    );
                    break;
                case "2":
                    countAnimals(species, counter);
                    break;
                case "c":
                    continue;
                case "b":
                    System.out.println("Returning..");
                default:
                    System.out.println("Invalid Input");
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
        if (counter <= 0) System.out.println("No species found.");

        //counter - 1 is the actual number of registered animals
        //
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
                return tmp;
            }
        }
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
}
