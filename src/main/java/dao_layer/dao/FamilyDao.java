package dao_layer.dao;

import happy_family.Family;

import java.util.List;

public interface FamilyDao {
    List<Family> getAllFamilies();

    Family getFamilyByIndex(Integer index);

    Boolean deleteFamily(Integer index);

    Boolean deleteFamily(Family family);

    void saveFamily(Family family);
}
