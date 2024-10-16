package dao_layer.service;

import dao_layer.dao.CollectionFamilyDao;
import happy_family.Family;
import happy_family.Human;

import java.util.ArrayList;
import java.util.List;

public class FamilyService {
    private List<Family> families = new ArrayList<>();

    public FamilyService() {
        this.families = new ArrayList<>();
    }

    public FamilyService(CollectionFamilyDao collectionFamilyDao) {

    }

    public void fillWithTestData() {
        Human father1 = new Human("John", "Doe", 1980L);
        Human mother1 = new Human("Jane", "Doe", 1985L);
        Family family1 = new Family(father1, mother1);

        Human father2 = new Human("Jim", "Beam", 1975L);
        Human mother2 = new Human("Jill", "Beam", 1980L);
        Family family2 = new Family(father2, mother2);

        families.add(family1);
        families.add(family2);
    }

    public List<Family> getAllFamilies() {
        return families;
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
         families.add(new Family(father, mother));
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
}
