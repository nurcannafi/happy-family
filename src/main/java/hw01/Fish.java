package hw01;

public class Fish extends Pet {

    public Fish(String nickname) {
        super(nickname);
        setSpecies(Species.FISH);
    }

    @Override
    public void respond() {
        System.out.println("Fish is swimming quietly.");
    }
}
