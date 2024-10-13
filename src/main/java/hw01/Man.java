package hw01;

import hw02.Family;

public class Man extends Human {

    public Man() {
    }

    public Man(String name, String surname, Integer dateOfBirthYear) {
        super(name, surname, dateOfBirthYear);
    }

    public Man(String name, String surname, Integer dateOfBirthYear, Family family) {
        super(name, surname, dateOfBirthYear, family);
    }

    public Man(String name, String surname, Integer dateOfBirthYear, Integer iq, Pet pet, Family family, String[][] schedule) {
        super(name, surname, dateOfBirthYear, iq, pet, family, schedule);
    }

    public void repairCar() {
        System.out.println("I am going to repair my car. Wanna help me out?");
    }

    @Override
    public void greetPet() {
        System.out.println(getName() + " greets pet");
    }
}
