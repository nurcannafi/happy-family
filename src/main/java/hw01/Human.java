package hw01;

import hw02.Family;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Human {

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
        this.schedule = new String[7][2];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i][0] = DayOfWeek.values()[i].name();
            schedule[i][1] = "Empty";
        }
    }

    public Human() {
    }

    public Human(String name, String surname, Integer dateOfBirthYear) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        this.schedule = new String[7][2];
        for (int i = 0; i < schedule.length; i++) {
            schedule[i][0] = DayOfWeek.values()[i].name();
            schedule[i][1] = "Empty";
        }
    }

    public Human(String name, String surname, Integer dateOfBirthYear, Integer iq, Pet pet, Family family, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        setIq(iq);
        this.pet = pet;
        this.family = family;
        this.schedule = (schedule != null) ? schedule : new String[7][2];
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = (schedule != null) ? schedule : new String[7][2];
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

    public void addToSchedule(DayOfWeek day, String activity) {
        int dayIndex = day.ordinal();
        if (!"Empty".equals(schedule[dayIndex][1])) {
            System.out.println("This day already has an activity: " + schedule[dayIndex][1]);
            return;
        }
        schedule[dayIndex][1] = activity;
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
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBirthYear=" + dateOfBirthYear +
                ", iq=" + iq +
                ", pet=" + pet +
                ", family=" + family +
                ", schedule=" + (schedule != null ? Arrays.deepToString(schedule) : "null") +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(dateOfBirthYear, human.dateOfBirthYear) && Objects.equals(iq, human.iq) && Objects.equals(pet, human.pet) && Objects.equals(family, human.family) && Objects.deepEquals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, dateOfBirthYear, iq, pet, family, Arrays.deepHashCode(schedule));
    }

    @SuppressWarnings({"deprecation", "removal"})

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Human object is being removed: " + this.getName() + " " + this.getSurname());
        super.finalize();
    }
}