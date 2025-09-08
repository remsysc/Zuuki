public class Animal {
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
}
