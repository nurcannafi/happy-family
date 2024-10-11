package hw01;

import java.util.Arrays;
import java.util.Objects;

public class Pet {

    private String[] habits;
    private Species species;
    private String nickname;
    private Integer age;
    private Integer trickLevel;


    public Pet(Species species, String nickname) {
        this.species = species;
        this.nickname = nickname;
    }

    public Pet(Species species, String nickname, Integer age, Integer trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        setTrickLevel(trickLevel);
        this.habits = habits;
    }

    public Pet() {
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
        return trickLevel;
    }

    public void setTrickLevel(Integer trickLevel) {
        if (trickLevel == null) this.trickLevel = null;
        else if (trickLevel >= 1 && trickLevel <= 100) {
            this.trickLevel = trickLevel;
        } else {
            throw new IllegalArgumentException("TrickLevel must be between 1 and 100, or null.");
        }
    }

    public void eat() {
        System.out.println("I am eating");
    }

    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!" + nickname);
    }

    public void foul() {
        System.out.println("I need to cover it up");
    }
  
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.deepEquals(habits, pet.habits) && Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname) && Objects.equals(age, pet.age) && Objects.equals(trickLevel, pet.trickLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(habits), species, nickname, age, trickLevel);
    }

    @SuppressWarnings({"deprecation", "removal"})

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Pet object is being removed: " + this.getNickname());
        super.finalize();
    }

    @Override
    public String toString() {
        return String.format("{habits=%s, species=%s, nickname='%s', age=%d, trickLevel=%d}", Arrays.toString(habits), species, nickname, age, trickLevel);
    }
}
