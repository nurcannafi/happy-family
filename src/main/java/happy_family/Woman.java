package happy_family;

import java.util.Map;

public class Woman extends Human {

    public Woman() {
    }

    public Woman(String name, String surname, Integer dateOfBirthYear) {
        super(name, surname, dateOfBirthYear);
    }

    public Woman(String name, String surname, Integer dateOfBirthYear, Family family) {
        super(name, surname, dateOfBirthYear, family);
    }

    public Woman(String name, String surname, Integer dateOfBirthYear, Integer iq, Pet pet, Family family, Map<DayOfWeek,String> schedule) {
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
