package hw02;

import hw01.Human;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FamilyDeleteChildWithObjectTest {

    private Family family;
    private Human mother;
    private Human father;
    private Human child1;
    private Human child2;

    @BeforeEach
    public void setUp() {
        mother = new Human("Jane", "Doe", 1975);
        father = new Human("Jon", "Doe", 1972);
        child1 = new Human("Jack", "Doe", 2000);
        child2 = new Human("Jill", "Doe", 2005);
        family = new Family(mother, father);

        family.setChildren(new Human[]{child1, child2});
    }

    @Test
    public void deleteChildSuccessTest() {
        assertTrue(family.deleteChild(child2));
        Human[] oneChild = family.getChildren();
        assertEquals(1, oneChild.length);
        assertEquals(child1, oneChild[0]);
    }

    @Test
    public void deleteChildFailureTest() {
        Human imaginaryChild = new Human("Imaginary", "Child", 2010);
        assertFalse(family.deleteChild(imaginaryChild));

        Human[] children = family.getChildren();
        assertEquals(2, children.length);
        assertEquals(child1, children[0]);
        assertEquals(child2, children[1]);
    }

}
