package hw02;

import hw01.Human;
import hw01.Pet;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class Family {

    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pet;

    static {
        System.out.println(Family.class.getSimpleName() + " class loaded.");
    }

    {
        System.out.println(this.getClass().getSimpleName() + " object created.");
    }

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
        this.pet = new HashSet<>();
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

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPet() {
        return pet;
    }

    public void setPet(Set<Pet> pet) {
        this.pet = pet;
    }

    public boolean deleteChild(int index) {
        if (index < 0 || index >= children.size()) {
            return false;
        }
        children.remove(index);
        return true;
    }

    public boolean deleteChild(Human child) {
            return children.remove(child);
    }

    public List<Human> addChild(Human child) {
        if (child != null) {
            children.add(child);
        }
        return children;
    }

    public int countFamily() {
        return children.size() + 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father) && Objects.equals(children, family.children) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, children, pet);
    }

    @SuppressWarnings({"deprecation", "removal"})

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Family object is being removed: " + this);
        super.finalize();
    }

    @Override
    public String toString() {
        return "Family{" +
                "mother=" + mother +
                ", father=" + father +
                ", children=" + children +
                ", pet=" + pet +
                '}';
    }
    public Set<Pet> getPets() {
        return pet;
    }

    public void setPets(Set<Pet> pets) {
        this.pet = pets;
    }
}
