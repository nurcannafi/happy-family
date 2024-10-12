package hw01;

public class RoboCat extends Pet {

    public RoboCat(String nickname) {
        super(nickname);
        setSpecies(Species.ROBOCAT);
    }

    @Override
    public void respond() {
        System.out.println("Beep-boop! I am a RoboCat, " + getNickname() + ". I am at your service.");
    }

    public void foul() {
        System.out.println("I malfunctioned, but I will fix it.");
    }
}
