package hw01;

public class Pet implements PetActions {

    private String[] habits;
    private String species;
    private String nickname;
    private Integer age;
    private Integer trickLevel;

    public Pet(String species, String nickname) {
        this.species = species;
        this.nickname = nickname;
        this.age = 0;
        this.trickLevel = 50;
        this.habits = new String[0];
    }

    public Pet(String species, String nickname, Integer age, Integer trickLevel, String[] habits) {
        this.species = species;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public Pet() {
        this.species = null;
        this.nickname = null;
        this.age = 0;
        this.trickLevel = 0;
        this.habits = new String[0];
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
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
        this.trickLevel = trickLevel;
    }

    @Override
    public void eat() {
        System.out.println("I am eating");
    }

    @Override
    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!" + nickname);
    }

    @Override
    public void foul() {
        System.out.println("I need to cover it up");
    }
}
