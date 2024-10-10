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

    public boolean deleteChild(int index) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(children[index])) {
                children[i] = null;
                return true;
            }
        }
        return false;
    }

    public boolean deleteChild(Human child) {
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child)) {
                children[i] = null;
                return true;
            }
        }
        return false;
    }

    public Human[] addChild(Human child) {
        if (child != null) {
            Human[] newChildren = Arrays.copyOf(children, children.length + 1);
            newChildren[children.length] = child;
            children = newChildren;
        }
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
                "mother=" + mother.toString() +
                ", father=" + father.toString() +
                ", children=" + Arrays.toString(children) +
                ", pet=" + pet.toString() +
                '}';
    }
}
