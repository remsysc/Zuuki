public class ZuukiApp {

    public static void main(String[] args) {
        //driver code

        zuukiApp();
    }

    public static void zuukiApp() {
        //TODO: CREATE A FUNCTIONAL UI HERE, THAT THROWS AN EXCEPTION WHENEVER NECESSARRY

        createAnimal();
        updateAnimal();
        deleteAnimal();
        displayAnimal();
    }

    public static void createAnimal() {
        //TODO: IMPLEMENT A FUNCTION THAT CREATE AN ANIMAL WITH ATTRIBUTES
        // SUCH NAME, AGE, ETC. REFER TO PDF
        // USE PARALLEL ARRAYS like name[i], age[i]
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

    public static void displayAnimal() {
        //TODO: IMPLEMENT A FUNCTION THAT DISPLAY AN ANIMAL LIST,
        // THIS DEPENDS IF THE USER PER TYPE , OR ALL OR AGE.
        // simply loop the arrays
    }
}
