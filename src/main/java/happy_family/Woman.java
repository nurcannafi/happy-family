package happy_family;

import java.util.Map;

public class Woman extends Human {

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
        System.out.println("Woman does make up");
    }

    @Override
    public void greetPet() {
        System.out.println(getName() + " greets pet");
    }

}
