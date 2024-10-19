package entity;

import model.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Fish extends Pet {

    private static final Logger logger = LoggerFactory.getLogger(Fish.class);

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
        logger.info("Fish is swimming quietly.");
    }

}
