package dao_layer.dao;

import happy_family.Family;

import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDao {

    private final List<Family> families;

    public CollectionFamilyDao() {
        families = new ArrayList<>();
    }

    @Override
    public List<Family> getAllFamilies() {
        return families;
    }

    @Override
    public Family getFamilyByIndex(Integer index) {
        if (index >= 0 && index < families.size()) {
            return families.get(index);
        }
        return null;
    }

    @Override
    public Boolean deleteFamily(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public Family saveFamily(Family family) {
        int index = families.indexOf(family);

        if (index != -1) {
            families.set(index, family);
        } else {
            families.add(family);
        }
        return family;

    }
}
