public class Animal implements Comparable<Animal> {
    private String name;
    private int age;
    private String species;
    private int enclosureNumber;

    // Constructor
    public Animal(String name, int age, String species, int enclosureNumber) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.enclosureNumber = enclosureNumber;
    }

    // Get functions
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public int getEnclosureNumber() {
        return enclosureNumber;
    }

    // Set functions
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setEnclosureNumber(int enclosureNumber) {
        this.enclosureNumber = enclosureNumber;
    }

    // Other useful functions
    public String getDisplayInfo() {
        return "Name: " + name + "\nAge: " + age + "\nSpecies: " + species + "\nEnclosure: " + enclosureNumber + "\n------------------\n";
    }

    // Method for comparison logic
    @Override
    public int compareTo(Animal a) {
        if (this.name.compareTo(a.name) != 0) {
            return this.name.compareTo(a.name);
        }

        if (this.age != a.age) {
            return this.age - a.age;
        }

        if (this.species.compareTo(a.species) != 0) {
            return this.species.compareTo(a.species);
        }

        return this.enclosureNumber - a.enclosureNumber;
    }
}
