package service;

import dao_layer.dao.CollectionFamilyDao;
import dao_layer.service.FamilyService;

import happy_family.DomesticCat;
import happy_family.Dog;
import happy_family.Family;
import happy_family.Human;
import happy_family.Man;
import happy_family.Pet;
import happy_family.RoboCat;
import happy_family.Woman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FamilyServiceTests {

    private CollectionFamilyDao familyDao;
    private FamilyService familyService;
    private Family family1;
    private Family family2;

    @BeforeEach
    void setup() {
        familyDao = new CollectionFamilyDao();
        familyService = new FamilyService(familyDao);

        family1 = new Family(new Woman("Jane", "Doe", 315532800000L),
                new Man("Jon", "Doe", 220752000000L));
        family2 = new Family(new Woman("Emily", "Smith", 473385600000L),
                new Man("Michael", "Smith", 378604800000L));

        family1.addChild(new Woman("Anna", "Doe", 1104537600000L));
        family1.addChild(new Man("Chris", "Doe", 1262304000000L));
        family2.addChild(new Woman("Sophie", "Smith", 1167609600000L));

        familyDao.saveFamily(family1);
        familyDao.saveFamily(family2);
    }

    @Test
    void getAllFamiliesTest() {
        List<Family> families = familyService.getAllFamilies();

        assertEquals(2, families.size());
        assertEquals(family1, families.get(0));
        assertEquals(family2, families.get(1));
    }

    @Test
    void getFamiliesBiggerThanTest() {
        List<Family> biggerFamilies = familyService.getFamiliesBiggerThan(3);

        assertEquals(1, biggerFamilies.size());
        assertEquals(family1, biggerFamilies.get(0));
    }

    @Test
    void getFamiliesLessThanTest() {
        List<Family> smallFamilies = familyService.getFamiliesLessThan(4);

        assertEquals(1, smallFamilies.size());
        assertEquals(family2, smallFamilies.get(0));
    }

    @Test
    void countFamiliesWithMemberNumberTest() {
        Long families = familyService.countFamiliesWithMemberNumber(3);
        assertEquals(1, families);
    }

    @Test
    void createNewFamilyTest() {
        Human mother = new Woman("Emily", "William", 852076800000L);
        Human father = new Man("Robert", "William", 725846400000L);
        familyService.createNewFamily(mother, father);

        List<Family> families = familyService.getAllFamilies();
        assertEquals(3, families.size());
        assertEquals(mother, families.get(2).getMother());
        assertEquals(father, families.get(2).getFather());
    }

    @Test
    void deleteFamilyByIndexSuccessTest() {
        assertTrue(familyService.deleteFamilyByIndex(1));
        assertEquals(1, familyService.getAllFamilies().size());
    }

    @Test
    void deleteFamilyByIndexFailureTest() {
        assertFalse(familyService.deleteFamilyByIndex(3));
        assertEquals(2, familyService.getAllFamilies().size());
    }

    @Test
    void bornChildTest() {
        String maleName = "Chris";
        String femaleName = "Anna";

        familyService.bornChild(family2, maleName, femaleName);
        List<Human> children = family2.getChildren();
        assertEquals(2, children.size());

        if (children.get(1).getName().equals(maleName)) {
            assertEquals(maleName, children.get(1).getName());
        } else {
            assertEquals(femaleName, children.get(1).getName());
        }
    }

    @Test
    void adoptChild() {
        Human child = new Human("Clara", "Alex", 1483228800000L);
        familyService.adoptChild(family1, child);

        assertEquals(3, family1.getChildren().size());
        assertEquals("Clara", family1.getChildren().get(2).getName());
    }

    @Test
    void deleteAllChildrenOlderThanTest() {
        familyService.deleteAllChildrenOlderThen(17);

        assertEquals(1, family1.getChildren().size());
        assertEquals(1, family2.getChildren().size());
        assertEquals("Chris", family1.getChildren().get(0).getName());
    }

    @Test
    void countTest() {
        assertEquals(2, familyService.getAllFamilies().size());

        Family family3 = new Family(new Woman("Anna", "Taylor", 631152000000L),
                new Man("David", "Taylor", 473385600000L));
        familyDao.saveFamily(family3);
        assertEquals(3, familyService.getAllFamilies().size());

        familyService.deleteFamilyByIndex(1);
        assertEquals(2, familyService.getAllFamilies().size());
    }

    @Test
    void getFamilyByIdTest() {
        Family retrievedFamily1 = familyService.getFamilyById(0);
        assertNotNull(retrievedFamily1);
        assertEquals(family1, retrievedFamily1);

        Family retrievedFamily2 = familyService.getFamilyById(1);
        assertNotNull(retrievedFamily2);
        assertEquals(family2, retrievedFamily2);

        assertNull(familyService.getFamilyById(3));
    }

    @Test
    void getPetsTest() {
        Pet dog = new Dog("Enzo");
        Pet cat = new DomesticCat("Tom");
        Set<Pet> pets = new HashSet<>();
        pets.add(dog);
        pets.add(cat);
        family1.setPet(pets);

        Set<Pet> domesticPets = familyService.getPets(0);
        assertEquals(2, domesticPets.size());
        assertTrue(domesticPets.contains(dog));
        assertTrue(domesticPets.contains(cat));
    }

    @Test
    void addPetsTest() {
        Pet dog = new Dog("Enzo");
        familyService.addPet(1, dog);

        Set<Pet> pet = familyService.getPets(1);
        assertNotNull(pet);
        assertEquals(1, pet.size());
        assertTrue(pet.contains(dog));

        Pet cat = new RoboCat("Tom");
        familyService.addPet(1, cat);
        assertEquals(2, familyService.getPets(1).size());
        assertTrue(familyService.getPets(1).contains(cat));
    }

}
