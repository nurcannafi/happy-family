package happy_family;

import java.util.Map;

public class Man extends Human {

    public Man() {
        super("Default Name", "Default Surname", 0L);
    }

    public Man(String name, String surname, long birthDate) {
        super(name, surname, birthDate);
    }

    public Man(String name, String surname, long birthDate, Family family) {
        super(name, surname, birthDate, family);
    }

    public Man(String name, String surname, long birthDate, Integer iq, Pet pet, Family family, Map<DayOfWeek, String> schedule) {
        super(name, surname, birthDate, iq, pet, family, schedule);
    }

    public void repairCar() {
        System.out.println("I am going to repair my car. Wanna help me out?");
    }

    @Override
    public void greetPet() {
        System.out.println(getName() + " greets pet");
    }
}
