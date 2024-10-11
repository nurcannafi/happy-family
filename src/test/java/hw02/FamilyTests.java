package hw02;

import hw01.Human;

import hw01.Pet;

import hw01.Species;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FamilyTests {

    private Family family;
    private Human mother;
    private Human father;
    private Human child1;
    private Human child2;
    private Pet pet;

    @BeforeEach
    public void setUp() {
        mother = new Human("Jane", "Doe", 1975);
        father = new Human("Jon", "Doe", 1972);
        child1 = new Human("Jack", "Doe", 2000);
        child2 = new Human("Jill", "Doe", 2005);
        pet = new Pet(Species.DOG, "Rock", 5, 60, new String[]{"eat", "drink", "sleep"});
        family = new Family(mother, father);

        family.setChildren(new Human[]{child1, child2});
        family.setPet(pet);
    }

    @Test
    public void deleteChildWithIndexSuccessTest() {
        assertTrue(family.deleteChild(0));
        Human[] oneChild = family.getChildren();
        assertEquals(1, oneChild.length);
        assertEquals(child2, oneChild[0]);
    }

    @Test
    public void deleteChildWithIndexFailureTest() {
        assertFalse(family.deleteChild(5));

        Human[] children = family.getChildren();
        assertEquals(2, children.length);
        assertEquals(child1, children[0]);
        assertEquals(child2, children[1]);
    }

    @Test
    public void deleteChildWithObjectSuccessTest() {
        assertTrue(family.deleteChild(child2));
        Human[] oneChild = family.getChildren();
        assertEquals(1, oneChild.length);
        assertEquals(child1, oneChild[0]);
    }

    @Test
    public void deleteChildWithObjectFailureTest() {
        Human imaginaryChild = new Human("Imaginary", "Child", 2010);
        assertFalse(family.deleteChild(imaginaryChild));

        Human[] children = family.getChildren();
        assertEquals(2, children.length);
        assertEquals(child1, children[0]);
        assertEquals(child2, children[1]);
    }

    @Test
    public void testToString() {
        String expected = "Family{" +
                "mother=" + mother.toString() +
                ", father=" + father.toString() +
                ", children=" + Arrays.toString(new Human[]{child1, child2}) +
                ", pet=" + pet.toString() + '}';

        assertEquals(expected, family.toString());
    }
}
