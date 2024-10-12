package hw01;

public class DomesticCat extends Pet {

    public DomesticCat(String nickname) {
        super(nickname);
        setSpecies(Species.CAT);
    }

    @Override
    public void respond() {
        System.out.println("Meow! I am " + getNickname() + ". I miss you!");
    }

    public void foul() {
        System.out.println("I need to cover my mess.");
    }

}
