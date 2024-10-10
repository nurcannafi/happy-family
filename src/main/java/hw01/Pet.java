package hw01;

import java.util.Arrays;

public class Pet {

    private String[] habits;
    private Species species;
    private String nickname;
    private Integer age;
    private Integer trickLevel;


    public Pet(Species species, String nickname) {
        this.species = Species.valueOf(String.valueOf(species));
        this.nickname = nickname;
    }

    public Pet(Species species, String nickname, Integer age, Integer trickLevel, String[] habits) {
        this.species = Species.valueOf(String.valueOf(species));
        this.nickname = nickname;
        this.age = age;
        setTrickLevel(trickLevel);
        this.habits = habits;
    }

    public Pet() {

    }

    public Pet(Species dog, String rock, int age, int trickLevel, String[] habits) {
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

    public void setSpecies(String species) {
        this.species = Species.valueOf(species);
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

    @Override
    public String toString() {
        return species + "{ nickname='" + nickname + '\'' +
                ", age=" + age + '\'' +
                ", trickLevel=" + trickLevel + '\'' +
                ", habits='" + Arrays.toString(habits) + "}";
    }
}
