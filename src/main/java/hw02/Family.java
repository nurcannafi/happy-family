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
        if (index < 0 || index >= children.length) {
            return false;
        }
        Human[] newChildren = new Human[children.length - 1];
        System.arraycopy(children, 0, newChildren, 0, index);
        if (index < children.length - 1) {
            System.arraycopy(children, index + 1, newChildren, index, children.length - index - 1);
        }
        children = newChildren;
        return true;
    }

    public boolean deleteChild(Human child) {
        Human[] newChildren = new Human[children.length - 1];
        int index = -1;
        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        return deleteChild(index);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && Objects.deepEquals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, Arrays.hashCode(children), pet);
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
