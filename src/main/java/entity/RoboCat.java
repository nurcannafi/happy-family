package entity;

import model.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class RoboCat extends Pet {

    private static final Logger logger = LoggerFactory.getLogger(RoboCat.class);

    public RoboCat(String nickname) {
        super(nickname);
        setSpecies(Species.ROBOCAT);
    }

    public RoboCat(String nickname, Integer age, Integer trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        setSpecies(Species.ROBOCAT);
    }

    @Override
    public void respond() {
        logger.info("Beep-boop! I am a RoboCat, {}. I am at your service.", getNickname());
    }

    public void foul() {
        logger.info("I malfunctioned, but I will fix it.");
    }

}
