package dao_layer.dao;

import happy_family.Family;

import java.io.IOException;
import java.util.List;

public interface FamilyDao {

    List<Family> getAllFamilies();

    Family getFamilyByIndex(Integer index);

    Boolean deleteFamily(int index);

    Boolean deleteFamily(Family family);

    Family saveFamily(Family family);

    void saveDataToFile(String filePath) throws IOException;

    void loadDataFromFile(String filePath) throws IOException, ClassNotFoundException;
}
