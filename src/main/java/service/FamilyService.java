package service;

import dao.impl.CollectionFamilyDao;
import entity.Human;
import entity.Pet;
import entity.Family;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FamilyService {

    private static final Logger logger = LoggerFactory.getLogger(FamilyService.class);

    private final CollectionFamilyDao familyDao;

    private static final String NAME_SMITH = "Smith";
    private static final String NAME_WILLIAMS = "Williams";
    private static final String NAME_JOHNSON = "Johnson";

    public FamilyService(CollectionFamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public void fillWithTestData() {
        Family[] families = {
                new Family(new Human("Emma", NAME_SMITH, 315619200000L),
                        new Human("John", NAME_SMITH, 315532800000L)),
                new Family(new Human("Olivia", NAME_JOHNSON, 576480000000L),
                        new Human("James", NAME_JOHNSON, 315619200000L)),
                new Family(new Human("Sophia", NAME_WILLIAMS, 631152000000L),
                        new Human("Liam", NAME_WILLIAMS, 576480000000L))
        };

        families[0].addChild(new Human("Alice", NAME_SMITH, 1262304000000L));
        families[0].addChild(new Human("Bob", NAME_SMITH, 1325376000000L));
        families[1].addChild(new Human("Charlie", NAME_JOHNSON, 1420070400000L));
        families[2].addChild(new Human("Mia", NAME_WILLIAMS, 1514764800000L));

        for (Family family : families) {
            familyDao.saveFamily(family);
        }

        logger.info("Test data has been added.");
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
            logger.warn("Invalid index.");
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
            logger.warn("Invalid family index.");
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
            logger.warn("Invalid index.");
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
                .toList();
    }

    public List<Family> displayFamiliesLessThan(int count) {
        return familyDao.getAllFamilies().stream()
                .filter(family -> countFamilyMembers(family) < count)
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
            logger.info("Data saved successfully.");
        } catch (IOException e) {
            logger.error("Failed to save data: {}", e.getMessage());
        }
    }

    public void loadDataFromFile(String filePath) {
        try {
            familyDao.loadDataFromFile(filePath);
            logger.info("Data loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Failed to load data: {}", e.getMessage());
        }
    }

}
