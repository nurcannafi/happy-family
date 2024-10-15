package happy_family;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FamilyTests {

    private Family family;
    private Human mother;
    private Human father;
    private Human child1;
    private Human child2;
    private Human child3;
    private Set<Pet> pets;

    @BeforeEach
    void setUp() {
        mother = new Human("Jane", "Doe",
                LocalDate.of(1975, 1, 1).toEpochDay() * 86400000L);
        father = new Human("Jon", "Doe",
                LocalDate.of(1972, 1, 1).toEpochDay() * 86400000L);
        child1 = new Human("Jack", "Doe",
                LocalDate.of(2000, 1, 1).toEpochDay() * 86400000L);
        child2 = new Human("Jill", "Doe",
                LocalDate.of(2005, 1, 1).toEpochDay() * 86400000L);
        child3 = new Human("Leonel", "Messi",
                LocalDate.of(2003, 1, 1).toEpochDay() * 86400000L);
        Pet pet = new Dog("Rock");
        pets = new HashSet<>();
        pets.add(pet);
        family = new Family(mother, father);

        family.addChild(child1);
        family.addChild(child2);
        family.addChild(child3);
        family.setPet(pets);
    }

    @Test
    void deleteChildWithIndexSuccessTest() {
        family.deleteChild(0);
        List<Human> twoChild = family.getChildren();
        assertEquals(2, twoChild.size());
        assertEquals(child2, twoChild.get(0));
        assertEquals(child3, twoChild.get(1));
    }

    @Test
    void deleteChildWithIndexFailureTest() {
        family.deleteChild(5);
        List<Human> children = family.getChildren();
        assertEquals(3, children.size());
        assertEquals(child1, children.get(0));
        assertEquals(child2, children.get(1));
        assertEquals(child3, children.get(2));
    }

    @Test
    void deleteChildWithObjectSuccessTest() {
        assertTrue(family.deleteChild(child2));
        List<Human> twoChild = family.getChildren();
        assertEquals(2, twoChild.size());
        assertEquals(child1, twoChild.get(0));
        assertEquals(child3, twoChild.get(1));
    }

    @Test
    void deleteChildWithObjectFailureTest() {
        Human imaginaryChild = new Human("Imaginary", "Child",
                LocalDate.of(2010, 1, 1).toEpochDay() * 86400000L);
        assertFalse(family.deleteChild(imaginaryChild));

        List<Human> threeChild = family.getChildren();
        assertEquals(3, threeChild.size());
        assertEquals(child1, threeChild.get(0));
        assertEquals(child2, threeChild.get(1));
        assertEquals(child3, threeChild.get(2));
    }

    @Test
    void addChildTest() {
        family.addChild(child3);
        assertEquals(4, family.getChildren().size());
        family.addChild(null);
        assertEquals(4, family.getChildren().size());
    }

    @Test
    void countFamilyTest() {
        assertEquals(5, family.countFamily());
        family.addChild(child3);
        assertEquals(6, family.countFamily());
        family.addChild(null);
        assertEquals(6, family.countFamily());
        family.deleteChild(1);
        assertEquals(5, family.countFamily());
        family.deleteChild(child3);
        assertEquals(4, family.countFamily());
        family.deleteChild(null);
        assertEquals(4, family.countFamily());
    }

    @Test
    void testToString() {
        String expected = String.format("Family{mother=%s, father=%s, children=%s, pet=%s}",
                mother, father, family.getChildren(), pets);

        assertEquals(expected, family.toString());
    }
}
