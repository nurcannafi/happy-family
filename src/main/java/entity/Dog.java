package entity;

import model.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Dog extends Pet {

    private static final Logger logger = LoggerFactory.getLogger(Dog.class);

    public Dog(String nickname) {
        super(nickname);
        setSpecies(Species.DOG);
    }

    public Dog(String nickname, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(Species.DOG);
    }

    @Override
    public void respond() {
        logger.info("Woof! I am {}. I am happy to see you!", getNickname());
    }

    public void foul() {
        logger.info("I made a mess, but I'll cover it up.");
    }

}
