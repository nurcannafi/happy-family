package hw02;

import hw01.Human;
import hw01.Pet;

import java.util.Arrays;
import java.util.Objects;

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

    public boolean deleteChild(Human child) {
        boolean isDeleted = false;
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child)) {
                children[i] = null;
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    public Human[] addChild(Human child) {
        Human[] newChildren = Arrays.copyOf(children, children.length + 1);
        newChildren[children.length] = child;
        children = newChildren;
        return children;
    }

    public int countFamily() {
        return children.length + 2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(father, mother);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Family that = (Family) obj;
        return Objects.equals(father, that.father) &&
                Objects.equals(mother, that.mother);
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + (mother != null ? mother.toString() : "none") +
                ", father=" + (father != null ? father.toString() : "none") +
                ", children=" + Arrays.toString(children) +
                ", pet=" + (pet != null ? pet.toString() : "none") +
                '}';
    }
}
