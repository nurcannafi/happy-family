package entity;

import model.DayOfWeek;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Human implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Human.class);
    private static final Random random = new Random();

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
        this.birthDate=birthDate;
        this.family = family;
        this.schedule = new EnumMap<>(DayOfWeek.class);
    }

    public Human() {
    }

    public Human(String name, String surname, Long birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate=birthDate;
        this.schedule = new EnumMap<>(DayOfWeek.class);
    }

    public Human(String name, String surname, Long birthDate, Integer iq, Pet pet, Family family, Map<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.birthDate=birthDate;
        setIq(iq);
        this.pet = pet;
        this.family = family;
        this.schedule = (schedule != null) ? new EnumMap<>(schedule) : new EnumMap<>(DayOfWeek.class);
    }

    public Human(String name, String surname, String birthDate, Integer iq) {
        this.name = name;
        this.surname = surname;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthDateLocal = LocalDate.parse(birthDate, formatter);
        this.birthDate = birthDateLocal.toEpochDay() * 86400000L;
        setIq(iq);
        this.schedule = new EnumMap<>(DayOfWeek.class);
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

    public int getAge() {
        LocalDate birthDateLocal = LocalDate.ofEpochDay(this.birthDate / 86400000L);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDateLocal, currentDate).getYears();
    }

    public String prettyFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("{name='%s', surname='%s', birthDate='%d', iq=%d, schedule=%s}",
                name, surname, birthDate, iq, schedule != null ? schedule.toString() : "null"));
        return sb.toString();
    }

    public void greetPet() {
        logger.info("Hello, {}", pet.getNickname());
    }

    public void addToSchedule(DayOfWeek day, String activity) {
        if (schedule.containsKey(day)) {
            if (logger.isInfoEnabled()) {
                String existingActivity = schedule.get(day);
                logger.info("This day already has an activity: {}", existingActivity);
            }
            return;
        }
        schedule.put(day, activity);
        if (logger.isInfoEnabled()) {
            logger.info("Added activity for {}: {}", day, activity);
        }
    }

    public void describePet() {
        if (pet != null) {
            if (logger.isInfoEnabled()) {
                String message = (pet.getTrickLevel() > 50)
                        ? "I have a %s that is %d years old; he is very sly."
                        : "I have a %s that is %d years old; he is almost not sly.";
                logger.info(String.format(message, pet.getSpecies(), pet.getAge()));
            }
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("I don't have a pet.");
            }
        }
    }

    public void feedPet(boolean isHungry) {
        if (!isHungry) {
            int pseudorandomNumber = random.nextInt(101);
            if (pseudorandomNumber > pet.getTrickLevel()) {
                logger.info("Hm... I will feed {}", pet.getNickname());
            } else {
                logger.info("I think {} is not hungry.", pet.getNickname());
            }
        } else {
            logger.info("Hm... I will feed {}", pet.getNickname());
        }
    }

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = (schedule != null) ? new EnumMap<>(schedule) : new EnumMap<>(DayOfWeek.class);
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
        logger.info("Human object is being removed: {} {}", this.getName(), this.getSurname());
        super.finalize();
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
