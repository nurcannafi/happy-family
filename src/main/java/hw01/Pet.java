package hw01;

import java.util.Arrays;
import java.util.Objects;

public abstract class Pet {

    private String[] habits;
    private String nickname;
    private Integer age;
    private Integer trickLevel;
    private Species species;

    public Pet(String nickname) {
        this.nickname = nickname;
        this.species = Species.UNKNOWN;
    }

    public Pet(String nickname, Integer age, Integer trickLevel, String[] habits) {
        this.nickname = nickname;
        this.age = age;
        setTrickLevel(trickLevel);
        this.habits = habits;
        this.species = Species.UNKNOWN;
    }

    public abstract void respond();

    public void eat() {
        System.out.println("I am eating");
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTrickLevel() {
        return (trickLevel != null) ? trickLevel : 0;
    }


    public void setTrickLevel(Integer trickLevel) {
        if (trickLevel == null) this.trickLevel = null;
        else if (trickLevel >= 1 && trickLevel <= 100) {
            this.trickLevel = trickLevel;
        } else {
            throw new IllegalArgumentException("TrickLevel must be between 1 and 100, or null.");
        }
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(nickname, pet.nickname) &&
                Objects.equals(age, pet.age) &&
                Objects.equals(trickLevel, pet.trickLevel) &&
                Arrays.equals(habits, pet.habits);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nickname, age, trickLevel);
        result = 31 * result + Arrays.hashCode(habits);
        return result;
    }

    @Override
    public String toString() {
        return String.format("{nickname='%s', age=%d, trickLevel=%d, habits=%s, species=%s}",
                nickname, age, trickLevel, Arrays.toString(habits), species);
    }
}
