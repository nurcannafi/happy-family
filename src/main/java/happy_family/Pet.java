package happy_family;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pet implements Serializable {

    private String nickname;
    private Integer age;
    private Integer trickLevel;
    private Species species;
    private Set<String> habits;

    protected Pet(String nickname) {
        this.nickname = nickname;
        this.species = Species.UNKNOWN;
        habits = new HashSet<>();
    }

    protected Pet(String nickname, Integer age, Integer trickLevel, Set<String> habits) {
        this.nickname = nickname;
        this.age = age;
        setTrickLevel(trickLevel);
        this.habits = new HashSet<>(habits);
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

    public String prettyFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("{species=").append(species)
                .append(", nickname='").append(nickname)
                .append("', age=").append(age)
                .append(", trickLevel=").append(trickLevel)
                .append(", habits=").append(habits).append("}");
        return sb.toString();
    }


    public void setTrickLevel(Integer trickLevel) {
        if (trickLevel == null) this.trickLevel = null;
        else if (trickLevel >= 1 && trickLevel <= 100) {
            this.trickLevel = trickLevel;
        } else {
            throw new IllegalArgumentException("TrickLevel must be between 1 and 100, or null.");
        }
    }

    public Set<String> getHabits() {
        return habits;
    }

    public void setHabits(Set<String> habits) {
        this.habits = (habits != null) ? new HashSet<>(habits) : new HashSet<>();
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
        return Objects.equals(habits, pet.habits) && Objects.equals(nickname, pet.nickname)
                && Objects.equals(age, pet.age) && Objects.equals(trickLevel, pet.trickLevel) && species == pet.species;
    }

    @Override
    public int hashCode() {
        return Objects.hash(habits, nickname, age, trickLevel, species);
    }

    @Override
    public String toString() {
        return String.format("%s{nickname='%s', age=%s, trickLevel=%s, habits=%s}",
                species, nickname, age, trickLevel, habits);
    }

}
