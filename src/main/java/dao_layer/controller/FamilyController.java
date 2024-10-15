package dao_layer.controller;

import happy_family.Human;
import happy_family.Pet;
import happy_family.Family;
import dao_layer.sevrice.FamilyService;

import java.util.List;

public class FamilyController {
    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    // for controlling null value
    private void validateFamily(Family family) {
        if (family == null) {
            throw new IllegalArgumentException("Family cannot be null.");
        }
    }

    public List<Family> getAllFamilies() {
        return familyService.getAllFamilies();
    }

    public void displayAllFamilies() {
        familyService.displayAllFamilies();
    }

    public List<Family> getFamiliesBiggerThan(int size) {
        return familyService.getFamiliesBiggerThan(size);
    }

    public List<Family> getFamiliesLessThan(int size) {
        return familyService.getFamiliesLessThan(size);
    }

    public long countFamiliesWithMemberNumber(int number) {
        return familyService.countFamiliesWithMemberNumber(number);
    }

    public void createNewFamily(Human mother, Human father) {
        familyService.createNewFamily(mother, father);
    }

    public boolean deleteFamilyByIndex(int index) {
        return familyService.deleteFamilyByIndex(index);
    }

    public Family bornChild(Family family, String maleName, String femaleName) {
        validateFamily(family);
        Family updatedFamily = familyService.bornChild(family, maleName, femaleName);
        return updatedFamily;
    }

    public Family adoptChild(Family family, Human child) {
        validateFamily(family);
        Family updatedFamily = familyService.adoptChild(family, child);
        return updatedFamily;
    }

    public void deleteAllChildrenOlderThen(int age) {
        familyService.deleteAllChildrenOlderThen(age);
    }

    public int count() {
        return familyService.count();
    }

    public Family getFamilyById(int id) {
        return familyService.getFamilyById(id);
    }

    public List<Pet> getPets(int familyIndex) {
        return familyService.getPets(familyIndex);
    }

    public void addPet(int familyIndex, Pet pet) {
        familyService.addPet(familyIndex, pet);
    }
}
