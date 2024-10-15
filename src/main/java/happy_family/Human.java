package happy_family;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Human {

    private String name;
    private String surname;
    private Long birthDate;
    private Integer iq;
    private Pet pet;
    private Family family;
    private Map<DayOfWeek, String> schedule;

    public Human(String name, String surname, Long birthDate, Family family) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.family = family;
        this.schedule = new HashMap<>();
    }

    public Human() {
    }

    public Human(String name, String surname, Long birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.schedule = new HashMap<>();
    }

    public Human(String name, String surname, Long birthDate, Integer iq, Pet pet, Family family, Map<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        setIq(iq);
        this.pet = pet;
        this.family = family;
        this.schedule = (schedule != null) ? schedule : new HashMap<>();
    }

    public Human(String name, String surname, String birthDateString, Integer iq) {
        this.name = name;
        this.surname = surname;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateLocal = LocalDate.parse(birthDateString, formatter);
        this.birthDate = birthDateLocal.toEpochDay() * 86400000L;
        setIq(iq);
        this.schedule = new HashMap<>();
    }

    public String describeAge() {
        LocalDate birthDateLocal = LocalDate.ofEpochDay(this.birthDate / 86400000L);
        LocalDate currentDate = LocalDate.now();
        long years = ChronoUnit.YEARS.between(birthDateLocal, currentDate);
        long months = ChronoUnit.MONTHS.between(birthDateLocal, currentDate) % 12;
        long days = ChronoUnit.DAYS.between(birthDateLocal, currentDate) % 30;
        Period age = Period.between(birthDateLocal, currentDate);
        return String.format("%d years, %d months, and %d days old", age.getYears(), age.getMonths(), age.getDays());
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
            int pseudorandomNumber = (int) (Math.random() * 101);
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

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
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
        return Objects.equals(name, human.name) && Objects.equals(surname, human.surname)
                && Objects.equals(birthDate, human.birthDate) && Objects.equals(iq, human.iq)
                && Objects.equals(pet, human.pet) && Objects.equals(family, human.family)
                && Objects.equals(schedule, human.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, iq, pet, family, schedule);
    }

    @SuppressWarnings({"deprecation", "removal"})

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Human object is being removed: " + this.getName() + " " + this.getSurname());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateLocal = LocalDate.ofEpochDay(this.birthDate / 86400000L);
        String formattedBirthDate = birthDateLocal.format(formatter);
        return String.format("Human{name='%s', surname='%s', birthDate=%s, iq=%d, pet=%s, family=%s, schedule=%s}",
                name, surname, formattedBirthDate, iq, pet, family, schedule);
    }

}
