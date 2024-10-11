package hw02;

import hw01.Human;
import hw01.Pet;

public class Family {

    private Human mother;
    private Human father;
    private Human[] children;
    private Pet pet;

    static {
        System.out.println(Family.class.getSimpleName() + " class loaded.");
    }

    {
        System.out.println(this.getClass().getSimpleName() + " object created.");
    }

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new Human[0];
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
