package hw06.dao;

import hw02.Family;

import java.util.List;

public interface FamilyDao {
    List<Family> getAllFamilies();

    Family getFamilyByIndex(Integer index);

    Boolean deleteFamily(Integer index);

    Boolean deleteFamily(Family family);

    void saveFamily(Family family);
}
