package entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Objects;

public class Family implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Family.class);

    private Human mother;
    private Human father;
    private List<Human> children;
    private Set<Pet> pets;

    public Family(Human mother, Human father) {
        this.mother = mother;
        this.father = father;
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
        logger.info("Family object created with mother: {}, father: {}", mother, father);
    }

    public Family() {
        this.children = new ArrayList<>();
        this.pets = new HashSet<>();
        logger.info("Empty Family object created.");
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

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public String prettyFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append("Family: \n")
                .append("\tMother: ").append(mother.prettyFormat()).append(",\n")
                .append("\tFather: ").append(father.prettyFormat()).append(",\n")
                .append("\tChildren: \n");

        for (Human child : children) {
            sb.append("\t\t").append(child.prettyFormat()).append("\n");
        }

        sb.append("\tPets: [");
        int petCount = 0;
        for (Pet p : pets) {
            sb.append(p.prettyFormat());
            petCount++;
            if (petCount < pets.size()) {
                sb.append(", ");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    public Optional<Human> deleteChild(int index) {
        if (index < 0 || index >= children.size()) {
            return Optional.empty();
        }
        return Optional.of(children.remove(index));
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

    public void removeChildrenOverAge(int age) {
        children.removeIf(child -> child.getAge() > age);
    }

    public void bornChild(String boyName, String girlName) {
        children.add(new Human(boyName, "Unknown", 0L));
        children.add(new Human(girlName, "Unknown", 0L));
    }

    public void adoptChild(Human child) {
        if (child != null) {
            children.add(child);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(mother, family.mother) && Objects.equals(father, family.father)
                && Objects.equals(children, family.children) && Objects.equals(pets, family.pets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mother, father, children, pets);
    }

    @Override
    @SuppressWarnings({"deprecation", "removal"})
    protected void finalize() throws Throwable {
        logger.info("Family object is being removed: {}", this);
        super.finalize();
    }

    @Override
    public String toString() {
        return String.format("Family{Mother=%s, Father=%s, Children=%s, Pets=%s}", mother, father, children, pets);
    }

}
