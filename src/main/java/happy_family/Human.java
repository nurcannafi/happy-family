package happy_family;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Human {

    private String name;
    private String surname;
    private Integer dateOfBirthYear;
    private Integer iq;
    private Pet pet;
    private Family family;
    private Map<DayOfWeek, String> schedule;

    public Human(String name, String surname, Integer dateOfBirthYear, Family family) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        this.family = family;
        this.schedule = new HashMap<>();
    }

    public Human() {
    }

    public Human(String name, String surname, Integer dateOfBirthYear) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        this.schedule = new HashMap<>();
    }

    public Human(String name, String surname, Integer dateOfBirthYear, Integer iq, Pet pet, Family family, Map<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        setIq(iq);
        this.pet = pet;
        this.family = family;
        this.schedule = (schedule != null) ? schedule : new HashMap<>();
    }

    public void greetPet() {
        System.out.println("Hello, " + pet.getNickname());
    }

    public void addToSchedule(DayOfWeek day, String activity) {
        if (schedule.containsKey(day)) {
            System.out.println("This day already has an activity: " + schedule.get(day));
            return;
        }
        schedule.put(day, activity);
    }

    public void describePet() {
        if (pet != null) {
            if (pet.getTrickLevel() > 50) {
                System.out.println("I have a " + pet.getSpecies() + " that is " + pet.getAge() + " years old; he is very sly.");
            } else {
                System.out.println("I have a " + pet.getSpecies() + " that is " + pet.getAge() + " years old; he is almost not sly.");
            }
        } else {
            System.out.println("I don't have a pet.");
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

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = (schedule != null) ? schedule : new HashMap<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(surname, human.surname) && Objects.equals(dateOfBirthYear, human.dateOfBirthYear) && Objects.equals(iq, human.iq) && Objects.equals(pet, human.pet) && Objects.equals(family, human.family) && Objects.equals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, dateOfBirthYear, iq, pet, family, schedule);
    }

    @SuppressWarnings({"deprecation", "removal"})

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Human object is being removed: " + this.getName() + " " + this.getSurname());
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
                ", schedule=" + schedule +
                '}';
    }
}