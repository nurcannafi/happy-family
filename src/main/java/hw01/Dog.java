package hw01;

public class Dog extends Pet {

    public Dog(String nickname) {
        super(nickname);
        setSpecies(Species.DOG);
    }

    @Override
    public void respond() {
        System.out.println("Woof! I am " + getNickname() + ". I am happy to see you!");
    }

    public void foul() {
        System.out.println("I made a mess, but I'll cover it up.");
    }
}
