package hw02;

import hw01.Human;

import hw01.Pet;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FamilyToStringTest {

    private Family family;
    private Human mother;
    private Human father;
    private Human child;
    private Pet pet;

    @BeforeEach
    public void setUp() {
        mother = new Human("Anna", "Karenina", 1987);
        father = new Human("Jack", "Karenina", 1986);
        child = new Human("Arthur", "Karenina", 2011);
        pet = new Pet("dog", "Rex", 5, 50, new String[]{"eat", "play"});

        family = new Family(mother, father);
        family.setChildren(new Human[]{child});
        family.setPet(pet);
    }

    @Test
    public void testToString() {
        String expected = "Family{" +
                "mother=" + mother.toString() +
                ", father=" + father.toString() +
                ", children=" + Arrays.toString(new Human[]{child}) +
                ", pet=" + pet.toString() + '}';

        assertEquals(expected, family.toString());
    }

}
