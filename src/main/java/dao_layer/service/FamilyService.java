package dao_layer.service;

import happy_family.Human;
import happy_family.Pet;
import happy_family.Family;

import dao_layer.dao.FamilyDao;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

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
        IntStream.range(0, families.size())
                .forEach(family -> System.out.println(family + " : " + families.get(family)));
    }

    public List<Family> getFamiliesBiggerThan(int size) {
        return getAllFamilies().stream()
                .filter(family -> family.countFamily() > size)
                .toList();
    }

    public List<Family> getFamiliesLessThan(int size) {
        return getAllFamilies().stream()
                .filter(family -> family.countFamily() < size)
                .toList();
    }

    public long countFamiliesWithMemberNumber(int number) {
        return getAllFamilies().stream()
                .filter(family -> family.countFamily() == number)
                .count();
    }

    public Family createNewFamily(Human parent1, Human parent2) {
        Family family = new Family(parent1, parent2);
        return familyDao.saveFamily(family);
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
        child.setBirthDate(System.currentTimeMillis());
        family.addChild(child);
        return familyDao.saveFamily(family);
    }

    public Family adoptChild(Family family, Human child) {
        family.addChild(child);
        return familyDao.saveFamily(family);
    }

    public void deleteAllChildrenOlderThen(int age) {
        getAllFamilies().stream().peek(family -> family.getChildren().removeIf(child -> {
                    LocalDate birthDateLocal = Instant.ofEpochMilli(child.getBirthDate())
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    int childAge = Period.between(birthDateLocal, LocalDate.now()).getYears();
                    return childAge > age;
                }))
                .forEach(familyDao::saveFamily);
    }

    public int count() {
        return getAllFamilies().size();
    }

    public Family getFamilyById(int id) {
        return familyDao.getFamilyByIndex(id);
    }

    public Set<Pet> getPets(int familyIndex) {
        Family family = getFamilyById(familyIndex);
        return family != null ? family.getPet() : Collections.emptySet();
    }

    public void addPet(int familyIndex, Pet pet) {
        Family family = getFamilyById(familyIndex);
        if (family != null) {
            family.getPet().add(pet);
            familyDao.saveFamily(family);
        }
    }
}
