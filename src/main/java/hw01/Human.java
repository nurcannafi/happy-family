package hw01;

import java.util.Random;

public class Human {
    private String name;
    private String surname;
    private Integer dateOfBirthYear;
    private Integer iq;
    private Pet pet;
    private Human mother;
    private Human father;
    private String[][] schedule;

    public Human(String name, String surname, Integer dateOfBirthYear, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        this.mother = mother;
        this.father = father;
    }

    public Human() {

    }

    public Human(String name, String surname, Integer dateOfBirthYear) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public Human(String name, String surname, Integer dateOfBirthYear, Integer iq, Pet pet, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        setIq(iq);
        this.pet = pet;
        this.mother = mother;
        this.father = father;
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

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public void greetPet() {
        System.out.println("Hello, " + pet.getNickname());
    }

    public void describePet() {
        if (pet.getTrickLevel() == null) return;
        if (pet.getTrickLevel() > 50) {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is very sly.");
        } else {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is almost not sly.");
        }

    }

    public void feedPet() {
        if (pet.isHungury() == false) {
            Random random = new Random();
            int pseudorandomNumber = random.nextInt(100);
            if (pseudorandomNumber > pet.getTrickLevel()) {
                System.out.println("Hm... I will feed " + pet.getNickname());
            } else {
                System.out.println("I think Jack is not hungry.");
            }
        } else {
            System.out.println("Hm... I will feed " + pet.getNickname());
            pet.setHungury(false);
        }
    }

    @Override
    public String toString() {
        return "Human{name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirthYear=" + dateOfBirthYear +
                ", iq=" + iq +
                ", mother=" + mother +
                ", father=" + father +
                ", pet=" + pet + "}";
    }
}
