package hw01;

public class Main {

    public static void main(String[] args) {
        Human father = new Human("Jon", "Potter", 1970);
        Human mother = new Human("Jane", "Potter", 1975);
        Human child = new Human("Michael", "Potter", 1997, father, mother);
        Pet dog = new Pet("dog", "Rock", 5, 75, new String[]{"eat", "drink", "sleep"});
        child.setPet(dog);
        child.greetPet();
        child.describePet();
        dog.eat();
        dog.foul();
        System.out.println(child);
        System.out.println(dog);
    }
}
