package entity;

import model.DayOfWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Woman extends Human {

    private static final Logger logger = LoggerFactory.getLogger(Woman.class);

    public Woman() {
    }

    public Woman(String name, String surname, Long dateOfBirthYear) {
        super(name, surname, dateOfBirthYear);
    }

    public Woman(String name, String surname, Long dateOfBirthYear, Family family) {
        super(name, surname, dateOfBirthYear, family);
    }

    public Woman(String name, String surname, Long dateOfBirthYear, Integer iq, Pet pet, Family family, Map<DayOfWeek, String> schedule) {
        super(name, surname, dateOfBirthYear, iq, pet, family, schedule);
    }

    public void makeUp() {
        logger.info("Woman does make up");
    }

    @Override
    public void greetPet() {
        logger.info("{} greets pet", getName());
    }

}
