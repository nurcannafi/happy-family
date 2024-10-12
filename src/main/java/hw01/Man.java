package hw01;

public class Man extends Human {

    public void repairCar() {
        System.out.println("I am going to repair my car. Wanna help me out?");
    }

    @Override
    public void greetPet() {
        System.out.println(getName() + " greets pet");
    }
}
