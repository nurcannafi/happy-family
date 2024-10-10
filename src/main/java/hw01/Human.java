package hw01;

import hw02.Family;

import java.util.Arrays;
import java.util.Random;

public class Human implements AutoCloseable {

    private String name;
    private String surname;
    private Integer dateOfBirthYear;
    private Integer iq;
    private Pet pet;
    private Family family;
    private String[][] schedule;

    public Human(String name, String surname, Integer dateOfBirthYear, Family family) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        this.family = family;
    }

    public Human() {
    }

    public Human(String name, String surname, Integer dateOfBirthYear) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public Human(String name, String surname, Integer dateOfBirthYear, Integer iq, Pet pet, Family family) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        setIq(iq);
        this.pet = pet;
        this.family = family;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDateOfBirthYear() {
        return dateOfBirthYear;
    }

    public void setDateOfBirthYear(Integer dateOfBirthYear) {
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public Integer getIq() {
        return iq;
    }

    public void setIq(Integer iq) {
        if (iq == null) {
            this.iq = null;
        } else if (iq >= 1 && iq <= 100) {
            this.iq = iq;
        } else {
            throw new IllegalArgumentException("IQ must be between 1 and 100, or null.");
        }
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void greetPet() {
        System.out.println("Hello, " + pet.getNickname());
    }

    public void describePet() {
        if (pet.getTrickLevel() > 50) {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is very sly.");
        } else {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is almost not sly.");
        }

    }

    public void feedPet(boolean isHungry) {
        if (!isHungry) {
            Random random = new Random();
            int pseudorandomNumber = random.nextInt(101);
            if (pseudorandomNumber > pet.getTrickLevel()) {
                System.out.println("Hm... I will feed " + pet.getNickname());
            } else {
                System.out.println("I think " + pet.getNickname() + " is not hungry.");
            }
        } else {
            System.out.println("Hm... I will feed " + pet.getNickname());
        }
    }

    @Override
    public void close() {
        System.out.println("Human object " + name + " " + surname + " is being removed.");
    }

    @Override
    public String toString() {
        return String.format("{name='%s', surname='%s', dateOfBirthYear=%d, iq=%d, pet=%s, family=%s, schedule=%s}", name, surname, dateOfBirthYear, iq, pet, family, Arrays.toString(schedule));
    }

}
