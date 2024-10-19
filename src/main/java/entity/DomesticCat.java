package entity;

import model.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class DomesticCat extends Pet {

    private static final Logger logger = LoggerFactory.getLogger(DomesticCat.class);

    public DomesticCat(String nickname) {
        super(nickname);
        setSpecies(Species.CAT);
    }

    public DomesticCat(String nickname, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(Species.CAT);
    }

    @Override
    public void respond() {
        logger.info("Meow! I am {}. I miss you!", getNickname());
    }

    public void foul() {
        logger.info("I need to cover my mess.");
    }

}
