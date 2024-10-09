package hw01;

public class Pet {

    String species;
    String nickname;
    int age;
    int trickLevel;
    String[] habits;

    public Pet(String species, String nickname) {
        this.species = species;
        this.nickname = nickname;
        this.age = 0;
        this.trickLevel = 50;
        this.habits = new String[0];
    }

    public Pet(String species, String nickname, int age, int trickLevel, String[] habits) {
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

    public void eat() {
        System.out.println("I am eating");
    }

    public void respond() {
        System.out.printf("Hello, owner. I am %s. I miss you!" + nickname);
    }

    public void foul() {
        System.out.println("I need to cover it up");
    }
}
