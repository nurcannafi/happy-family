package happy_family;

import java.util.Set;

public class Fish extends Pet {

    public Fish(String nickname) {
        super(nickname);
        setSpecies(Species.FISH);
    }

    public Fish(String nickname, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(Species.FISH);
    }

    @Override
    public void respond() {
        System.out.println("Fish is swimming quietly.");
    }
}
