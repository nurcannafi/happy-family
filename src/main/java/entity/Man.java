package entity;

import model.DayOfWeek;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Man extends Human {

    private static final Logger logger = LoggerFactory.getLogger(Man.class);

    public Man() {
        super("Default Name", "Default Surname", 0L);
    }

    public Man(String name, String surname, Long birthDate) {
        super(name, surname, birthDate);
    }

    public Man(String name, String surname, Long birthDate, Family family) {
        super(name, surname, birthDate, family);
    }

    public Man(String name, String surname, Long birthDate, Integer iq, Pet pet, Family family,
               Map<DayOfWeek, String> schedule) {
        super(name, surname, birthDate, iq, pet, family, schedule);
    }

    public void repairCar() {
        logger.info("I am going to repair my car. Wanna help me out?");
    }

    @Override
    public void greetPet() {
        logger.info("{} greets pet", getName());
    }

}
