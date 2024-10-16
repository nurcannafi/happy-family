package dao_layer.service;

import dao_layer.dao.FamilyDao;
import happy_family.Family;
import happy_family.Human;
import happy_family.Pet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class FamilyService {

    private final FamilyDao familyDao;
    private List<Family> families = new ArrayList<>();


    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
        this.families = familyDao.getAllFamilies();
    }

    public void fillWithTestData() {
        Human father1 = new Human("John", "Doe", 1980L);
        Human mother1 = new Human("Jane", "Doe", 1985L);
        Family family1 = new Family(mother1, father1);

        Human father2 = new Human("Jim", "Beam", 1975L);
        Human mother2 = new Human("Jill", "Beam", 1980L);
        Family family2 = new Family(mother2, father2);

        families.add(family1);
        families.add(family2);
    }

    public List<Family> getAllFamilies() {
        return families;
    }

    public void displayAllFamilies() {
        families.forEach(System.out::println);
    }

    public List<Family> getFamiliesBiggerThan(int size) {
        List<Family> result = new ArrayList<>();
        for (Family family : families) {
            if (family.countFamily() > size) {
                result.add(family);
            }
        }
        return result;
    }

    public List<Family> getFamiliesLessThan(int size) {
        List<Family> result = new ArrayList<>();
        for (Family family : families) {
            if (family.countFamily() < size) {
                result.add(family);
            }
        }
        return result;
    }

    public long countFamiliesWithMemberNumber(int number) {
        return families.stream()
                .filter(family -> family.countFamily() == number)
                .count();
    }

    public Family createNewFamily(Human mother, Human father) {
        families.add(new Family(mother, father));
        return families.get(families.size() - 1);
    }

    public boolean deleteFamilyByIndex(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        }
        return false;
    }

    public Family getFamilyById(int index) {
        if (index >= 0 && index < families.size()) {
            return families.get(index);
        }
        return null;
    }

    public void deleteAllChildrenOlderThan(int age) {
        for (Family family : families) {
            family.removeChildrenOverAge(age);
        }
    }

    public int count() {
        return getAllFamilies().size();
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
        Family family = getFamilyById(familyIndex);
        return family != null ? family.getPets() : Collections.emptySet();
    }

    public void addPet(int familyIndex, Pet pet) {
        Family family = getFamilyById(familyIndex);
        if (family != null) {
            family.getPets().add(pet);
            familyDao.saveFamily(family);
        }
    }
}
