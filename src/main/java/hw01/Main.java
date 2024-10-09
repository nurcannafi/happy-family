package hw01;

public class Main {

    public static void main(String[] args) {
        Human fatherJon = new Human("Jon", "Potter", 1970);
        Human motherJane = new Human("Jane", "Potter", 1975);
        Human childMichel = new Human("Michael", "Potter", 1997, fatherJon, motherJane);
        Human childHarry = new Human();
        Pet dogRock = new Pet("dog", "Rock", 5, 60, new String[]{"eat", "drink", "sleep"}, false);
        Pet dogGuffy = new Pet("dog", "Guffy");
        Pet catGarfield = new Pet();
        childHarry.setPet(dogGuffy);
        childHarry.greetPet();
        childHarry.describePet();
        dogGuffy.eat();
        dogGuffy.foul();
        System.out.println(childHarry);
        System.out.println(dogGuffy);
        System.out.println("-------------------------");
        childMichel.setPet(dogRock);
        childMichel.feedPet();
        childMichel.greetPet();
        childMichel.describePet();
        dogRock.eat();
        dogRock.foul();
        System.out.println(childMichel);
        System.out.println(dogRock);
        System.out.println("-------------------------");
        motherJane.setPet(catGarfield);
        motherJane.greetPet();
        motherJane.describePet();
        System.out.println(motherJane);
        System.out.println(catGarfield);
    }
}
