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
        //TODO: IMPLEMENT A FUNCTION THAT DISPLAY AN ANIMAL LIST,
        //search animal by name, count how many animals belogn to specific species
        // and display all animals housed in a specific enclosure
        //
        //display options
        //

        int count = countAnimals(10, enclosure_number, counter);

        // searchAnimalByName(
        //        "rem",
        //        name,
        //        age,
        //        species,
        //        enclosure_number,
        //        counter
        //    );
    }

    public static void displayAnimalsByEnclosureNumber(
        int userInput,
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {}

    //method overloading
    public static int countAnimals(
        String userInput,
        String[] species,
        int counter
    ) {
        int total = 0;

        for (int i = 0; i < counter; i++) {
            if (userInput.equalsIgnoreCase(species[i])) total++;
        }

        return total;
    }

    public static int countAnimals(
        int userInput,
        int[] enclosure_number,
        int counter
    ) {
        int total = 0;

        for (int i = 0; i < counter; i++) {
            if (enclosure_number[i] == userInput) total++;
        }

        return total;
    }

    public static void searchAnimalByName(
        String userInput,
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        int index = isNameValid(userInput, name, counter);

        if (index < 0) return;

        //display all information regarding the found userInput

        System.out.println("Name: " + name[index]);
        System.out.println("Age: " + age[index]);
        System.out.println("Species: " + species[index]);
        System.out.println("Enclosure Number: " + enclosure_number[index]);
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
}
