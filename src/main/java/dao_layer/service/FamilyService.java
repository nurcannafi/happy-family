package dao_layer.service;

import dao_layer.dao.CollectionFamilyDao;
import happy_family.Family;
import happy_family.Human;
import happy_family.Pet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FamilyService {

    private final CollectionFamilyDao familyDao;

    public FamilyService(CollectionFamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public void fillWithTestData() {
        Family[] families = {
                new Family(new Human("Emma", "Smith", 315619200000L),
                        new Human("John", "Smith", 315532800000L)),
                new Family(new Human("Olivia", "Johnson", 576480000000L),
                        new Human("James", "Johnson", 315619200000L)),
                new Family(new Human("Sophia", "Williams", 631152000000L),
                        new Human("Liam", "Williams", 576480000000L))
        };

        families[0].addChild(new Human("Alice", "Smith", 1262304000000L));
        families[0].addChild(new Human("Bob", "Smith", 1325376000000L));
        families[1].addChild(new Human("Charlie", "Johnson", 1420070400000L));
        families[2].addChild(new Human("Mia", "Williams", 1514764800000L));

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
        families.forEach(family -> {
            family.getChildren().removeIf(child -> child.getAge() > age);
            familyDao.saveFamily(family);
        });
    }

    public List<Family> displayFamiliesBiggerThan(int count) {
        return familyDao.getAllFamilies().stream()
                .filter(family -> countFamilyMembers(family) > count)
                .peek(System.out::println)
                .toList();
    }

    public List<Family> displayFamiliesLessThan(int count) {
        return familyDao.getAllFamilies().stream()
                .filter(family -> countFamilyMembers(family) < count)
                .peek(System.out::println)
                .toList();
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
        return getFamilyByIndex(familyIndex)
                .map(Family::getPets).orElse(Collections.emptySet());
    }

    public void addPet(int familyIndex, Pet pet) {
        getFamilyByIndex(familyIndex).ifPresent(family -> {
            family.getPets().add(pet);
            familyDao.saveFamily(family);
        });
    }
    public void saveDataToFile(String filePath) {
        try {
            familyDao.saveDataToFile(filePath);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    public void loadDataFromFile(String filePath) {
        try {
            familyDao.loadDataFromFile(filePath);
            System.out.println("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
    }
}
