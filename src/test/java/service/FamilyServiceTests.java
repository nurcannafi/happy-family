package service;

import dao.impl.CollectionFamilyDao;

import entity.DomesticCat;
import entity.Dog;
import entity.Family;
import entity.Human;
import entity.Man;
import entity.Pet;
import entity.Woman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        List<Family> biggerFamilies = familyService.displayFamiliesBiggerThan(3);

        assertEquals(1, biggerFamilies.size());
        assertEquals(family1, biggerFamilies.get(0));
    }

    @Test
    void getFamiliesLessThanTest() {
        List<Family> smallFamilies = familyService.displayFamiliesLessThan(4);

        assertEquals(1, smallFamilies.size());
        assertEquals(family2, smallFamilies.get(0));
    }

    @Test
    void countFamiliesWithMemberNumberTest() {
        long families = familyService.countFamilyMembers(family1);
        assertEquals(4, families);
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
        familyService.deleteFamilyByIndex(1);
        assertEquals(1, familyService.getAllFamilies().size());
    }


    @Test
    void deleteFamilyByIndexFailureTest() {
        familyService.deleteFamilyByIndex(3);
        assertEquals(2, familyService.getAllFamilies().size());
    }

    @Test
    void bornChildTest() {
        String maleName = "Chris";
        String femaleName = "Anna";

        familyService.bornChild(family2, maleName, femaleName);
        List<Human> children = family2.getChildren();
        assertEquals(2, children.size());

        String childName = children.get(1).getName();
        assertTrue(childName.equals(maleName) || childName.equals(femaleName));
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
        familyService.deleteChildrenOlderThan(17);

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
        Family retrievedFamily1 = familyService.getFamilyByIndex(0).orElse(null);
        assertNotNull(retrievedFamily1);
        assertEquals(family1, retrievedFamily1);

        Family retrievedFamily2 = familyService.getFamilyByIndex(1).orElse(null);
        assertNotNull(retrievedFamily2);
        assertEquals(family2, retrievedFamily2);

        assertTrue(familyService.getFamilyByIndex(3).isEmpty());
    }

    @Test
    void getPetsTest() {
        Pet dog = new Dog("Enzo");
        Pet cat = new DomesticCat("Tom");
        Set<Pet> pets = new HashSet<>();
        pets.add(dog);
        pets.add(cat);
        family1.setPets(pets);

        Set<Pet> domesticPets = familyService.getPets(0);
        assertEquals(2, domesticPets.size());
        assertTrue(domesticPets.contains(dog));
        assertTrue(domesticPets.contains(cat));
    }

    @Test
    void addPetTest() {
        Pet dog = new Dog("Rocky");
        familyService.addPet(0, dog);

        assertEquals(1, family1.getPets().size());
        assertEquals("Rocky", family1.getPets().iterator().next().getNickname());
    }
}