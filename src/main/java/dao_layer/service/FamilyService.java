package dao_layer.service;

import happy_family.Human;
import happy_family.Pet;
import happy_family.Family;

import dao_layer.dao.FamilyDao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyService {

    private final FamilyDao familyDao;

    public FamilyService(FamilyDao familyDao) {
        this.familyDao = familyDao;
    }

    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    public void displayAllFamilies() {
        List<Family> families = getAllFamilies();
        families.stream()
                .forEach(family -> System.out.println(families.indexOf(family) + " : " + family));
    }

    public List<Family> getFamiliesBiggerThan(int size) {
        return getAllFamilies().stream()
                .filter(family -> family.countFamily() > size)
                .collect(Collectors.toList());
    }

    public List<Family> getFamiliesLessThan(int size) {
        return getAllFamilies().stream()
                .filter(family -> family.countFamily() < size)
                .collect(Collectors.toList());
    }

    public long countFamiliesWithMemberNumber(int number) {
        return getAllFamilies().stream()
                .filter(family -> family.countFamily() == number)
                .count();
    }

    public void createNewFamily(Human parent1, Human parent2) {
        Family family = new Family(parent1, parent2);
        familyDao.saveFamily(family);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    public Family bornChild(Family family, String maleName, String femaleName) {
        Human child = new Human();
        if (Math.random() < 0.5) {
            child.setName(maleName);
        } else {
            child.setName(femaleName);
        }
        child.setDateOfBirthYear(LocalDate.now().getYear());
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        familyDao.saveFamily(family);
        return family;
    }

    public void deleteAllChildrenOlderThen(int age) {
        for (Family family : getAllFamilies()) {
            family.getChildren().removeIf(child -> child.getAge() > age);
            familyDao.saveFamily(family);
        }
    }

    public int count() {
        return getAllFamilies().size();
    }

    public Family getFamilyById(int id) {
        return familyDao.getFamilyByIndex(id);
    }

    public List<Pet> getPets(int familyIndex) {
        Family family = getFamilyById(familyIndex);
        return family != null ? family.getPet().stream().collect(Collectors.toList()) : null;
    }

    public void addPet(int familyIndex, Pet pet) {
        Family family = getFamilyById(familyIndex);
        if (family != null) {
            family.getPet().add(pet);
            familyDao.saveFamily(family);
        }
    }
}
