package dao_layer.service;

import dao_layer.dao.CollectionFamilyDao;
import happy_family.Family;
import happy_family.Human;
import happy_family.Pet;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FamilyService {
    private CollectionFamilyDao familyDao;


    public FamilyService() {
        this.familyDao = new CollectionFamilyDao();
    }


    public void fillWithTestData() {
        Family[] families = {
                new Family(new Human("Emma", "Smith", 1985L), new Human("John", "Smith", 1980L)),
                new Family(new Human("Olivia", "Johnson", 1988L), new Human("James", "Johnson", 1985L)),
                new Family(new Human("Sophia", "Williams", 1990L), new Human("Liam", "Williams", 1989L))
        };

        families[0].addChild(new Human("Alice", "Smith", 2010L));
        families[0].addChild(new Human("Bob", "Smith", 2012L));
        families[1].addChild(new Human("Charlie", "Johnson", 2015L));
        families[2].addChild(new Human("Mia", "Williams", 2018L));

        for (Family family : families) {
            familyDao.saveFamily(family);
        }

        System.out.println("Test data has been added.");
    }


    public Family createNewFamily(Human mother, Human father) {
        Family newFamily = new Family(mother, father);
        familyDao.saveFamily(newFamily);
        return newFamily;
    }


    public void deleteFamilyByIndex(int index) {
        List<Family> families = familyDao.getAllFamilies();
        if (index >= 0 && index < families.size()) {
            familyDao.deleteFamily(index);
        } else {
            System.out.println("Invalid index.");
        }
    }


    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }


    public void addChildToFamily(int familyIndex, Human child) {
        List<Family> families = familyDao.getAllFamilies();
        if (familyIndex >= 0 && familyIndex < families.size()) {
            Family family = families.get(familyIndex);
            family.addChild(child);
            familyDao.saveFamily(family);
        } else {
            System.out.println("Invalid family index.");
        }
    }


    public int countFamilies() {
        return familyDao.getAllFamilies().size();
    }


    public int countFamilyMembers(Family family) {
        return family.getChildren().size() + 2;
    }


    public Optional<Family> getFamilyByIndex(int index) {
        List<Family> families = familyDao.getAllFamilies();
        if (index >= 0 && index < families.size()) {
            return Optional.of(families.get(index));
        } else {
            System.out.println("Invalid index.");
            return Optional.empty();
        }
    }


    public void saveFamily(Family family) {
        familyDao.saveFamily(family);
    }


    public void deleteChildrenOlderThan(int age) {
        List<Family> families = familyDao.getAllFamilies();
        for (Family family : families) {
            family.getChildren().removeIf(child -> child.getAge() > age);
            familyDao.saveFamily(family);
        }
    }


    public List<Family> displayFamiliesBiggerThan(int count) {
        List<Family> families = familyDao.getAllFamilies();
        for (Family family : families) {
            if (countFamilyMembers(family) > count) {
                System.out.println(family);
            }
        }
        return families;
    }


    public List<Family> displayFamiliesLessThan(int count) {
        List<Family> families = familyDao.getAllFamilies();
        for (Family family : families) {
            if (countFamilyMembers(family) < count) {
                System.out.println(family);
            }
        }
        return families;
    }


    public Family bornChild(Family family, String maleName, String femaleName) {
        Human child = new Human();
        if (Math.random() < 0.5) {
            child.setName(maleName);
        } else {
            child.setName(femaleName);
        }
        child.setBirthDate(System.currentTimeMillis());
        family.addChild(child);
        return familyDao.saveFamily(family);
    }


    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        return familyDao.saveFamily(family);
    }


    public Set<Pet> getPets(int familyIndex) {
        Family family = getFamilyByIndex(familyIndex).orElse(null);
        return family != null ? family.getPets() : Collections.emptySet();
    }


    public void addPet(int familyIndex, Pet pet) {
        Family family = getFamilyByIndex(familyIndex).orElse(null);
        if (family != null) {
            family.getPets().add(pet);
            familyDao.saveFamily(family);
        }
    }
}
