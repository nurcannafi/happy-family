package hw01;

public class Woman extends Human {

    public void makeUp() {
        System.out.println("Woman does make up");
    }

    @Override
    public void greetPet() {
        System.out.println(getName() + " greets pet");
    }
}
