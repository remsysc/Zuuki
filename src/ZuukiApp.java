import java.util.InputMismatchException;
import java.util.Scanner;

public class ZuukiApp {

    public static void main(String[] args) {
        //driver code

        zuukiApp();
    }

    public static void zuukiApp() {
        //TODO: CREATE A FUNCTIONAL UI HERE, THAT THROWS AN EXCEPTION WHENEVER NECESSARRY

        
        final int kSize = 50;  // constant
        
        //initialize size of arrays with kSize
        String[] name = new String[kSize];
        int[] age = new int[kSize];
        String[] species = new String[kSize];
        int[] enclosure_number = new int[kSize];
        
        int counter = 0; //tracks the available index

        counter = createAnimal(name, age, species, enclosure_number, counter); //simply updates the counter
        updateAnimal();
        deleteAnimal();
        displayAnimal(name, age, species, enclosure_number, counter);
    }

    public static int createAnimal(
        String[] name,
        int age[],
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //TODO: IMPLEMENT A FUNCTION THAT CREATE AN ANIMAL WITH ATTRIBUTES
        // SUCH NAME, AGE, ETC. REFER TO PDF
        // USE PARALLEL ARRAYS like name[i], age[i]

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

                counter++; //increment since the method is done
                isCleanInputs = true; //break of loop
            } catch (NumberFormatException e) {
                //loop back if input aint clean
                System.out.println("Invalid Input: " + e.getMessage()); //echo the error
                System.out.println("Please try again.");
            }
        } while (!isCleanInputs);

        scan.close(); //close the scanner
        return counter;
    }

    public static void updateAnimal() {
        //TODO: IMPLEMENT A FUNCTION THAT UPDATES ANIMAL DETAILS
        // same as above
    }

    public static void deleteAnimal() {
        //TODO: IMPLEMENT A FUNCTION THAT DELETE AN ANIMAL
        // either simply marks the animal as "deleted"
        // or shift them towards the end of an array
    }

    public static void displayAnimal(
        String[] name,
        int[] age,
        String[] species,
        int[] enclosure_number,
        int counter
    ) {
        //TODO: IMPLEMENT A FUNCTION THAT DISPLAY AN ANIMAL LIST,
        // THIS DEPENDS IF THE USER PER TYPE , OR ALL OR AGE.
        // simply loop the arrays
        //

        for (int i = 0; i < counter; i++) {
            System.out.println(
                name[i] +
                " " +
                age[i] +
                " " +
                species[i] +
                " " +
                enclosure_number[i]
            );
        }
    }
}
