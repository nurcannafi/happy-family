package controller;

import exception.FamilyOverFlowException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FamilyService;
import entity.Family;
import entity.Human;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FamilyController {

    private static final Logger logger = LoggerFactory.getLogger(FamilyController.class);
    private final FamilyService familyService;
    private final Scanner scanner;
    private static final String ENTER_FAMILY_INDEX_MESSAGE = "Enter the family index: ";

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMainMenu();
            int command = getUserInput("Select a command: ");

            switch (command) {
                case 1 -> fillWithTestData();
                case 2 -> displayAllFamilies();
                case 3 -> displayFamiliesBiggerThan();
                case 4 -> displayFamiliesLessThan();
                case 5 -> countFamiliesWithMembers();
                case 6 -> createNewFamily();
                case 7 -> deleteFamilyByIndex();
                case 8 -> editFamilyByIndex();
                case 9 -> deleteAllChildrenOlderThan();
                case 10 -> running = false;
                default -> logger.warn("Invalid command! Please select a valid command.");
            }
        }
    }

    public void showMainMenu() {
        logger.info("\nChoose a command:");
        logger.info("1. Fill with test data");
        logger.info("2. Display the entire list of families");
        logger.info("3. Display families bigger than a specified number");
        logger.info("4. Display families less than a specified number");
        logger.info("5. Count families with a specific number of members");
        logger.info("6. Create a new family");
        logger.info("7. Delete a family by index");
        logger.info("8. Edit a family by index");
        logger.info("9. Remove all children over the age of majority");
        logger.info("10. Exit");
    }

    private int getUserInput(String message) {
        while (true) {
            logger.info(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                logger.warn("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void fillWithTestData() {
        familyService.fillWithTestData();
        logger.info("Test data has been added.");
    }

    private void displayAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            logger.info("The family list is empty.");
        } else {
            families.forEach(family -> logger.info(family.toString()));
        }
    }

    private void displayFamiliesBiggerThan() {
        int size = getUserInput("Enter the maximum family size: ");
        List<Family> families = familyService.displayFamiliesLessThan(size);

        if (families.isEmpty()) {
            logger.info("No families found for the maximum size: {}", size);
        } else {
            logger.info("Displaying families with less than {} members:", size);
            families.forEach(family -> logger.info(family.toString()));
        }
    }

    private void displayFamiliesLessThan() {
        int size = getUserInput("Enter the maximum family size: ");
        List<Family> families = familyService.displayFamiliesLessThan(size);
        if (families.isEmpty()) {
            logger.info("No families found.");
        } else {
            families.forEach(family -> logger.info(family.toString()));
        }
    }

    private void countFamiliesWithMembers() {
        int familyIndex = getUserInput(ENTER_FAMILY_INDEX_MESSAGE);
        familyService.getFamilyByIndex(familyIndex)
                .ifPresentOrElse(
                        family -> {
                            int count = familyService.countFamilyMembers(family);
                            logger.info("Number of family members: {}", count);
                        },
                        () -> logger.info("Invalid family index."));
    }

    private void createNewFamily() {
        Human mother = getHumanDetails("mother");
        Human father = getHumanDetails("father");
        familyService.createNewFamily(mother, father);
        logger.info("New family created.");
    }

    private Human getHumanDetails(String role) {
        logger.info("Enter details for the {}:", role);
        logger.info("Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        logger.info("Last Name: ");
        String lastName = scanner.nextLine();
        long birthYear = getUserInput("Birth Year: ");
        return new Human(name, lastName, birthYear);
    }

    private void deleteFamilyByIndex() {
        int index = getUserInput(ENTER_FAMILY_INDEX_MESSAGE) - 1;
        familyService.deleteFamilyByIndex(index);
        logger.info("Family deleted if the index was valid.");
    }

    private void editFamilyByIndex() {
        int index = getUserInput(ENTER_FAMILY_INDEX_MESSAGE) - 1;
        Family family = familyService.getFamilyByIndex(index).orElse(null);
        if (family == null) {
            logger.info("Family not found.");
            return;
        }

        logger.info("1. Give birth to a child");
        logger.info("2. Adopt a child");
        int editChoice = getUserInput("Choose an option: ");

        switch (editChoice) {
            case 1 -> handleChildBirth(family);
            case 2 -> handleChildAdoption(family);
            default -> logger.info("Invalid choice.");
        }
    }

    private void handleChildBirth(Family family) {
        if (family.countFamily() > 4) {
            throw new FamilyOverFlowException("Reached the maximum number of family members!");
        }
        logger.info("Son's name: ");
        scanner.nextLine(); // Clear the buffer
        String maleName = scanner.nextLine();
        logger.info("Daughter's name: ");
        String femaleName = scanner.nextLine();
        familyService.bornChild(family, maleName, femaleName);
        logger.info("Child born.");
    }

    private void handleChildAdoption(Family family) {
        if (family.countFamily() > 4) {
            throw new FamilyOverFlowException("Reached the maximum number of family members!");
        }
        logger.info("Child's name: ");
        scanner.nextLine();
        String childName = scanner.nextLine();
        long childBirthYear = getUserInput("Child's birth year: ");
        Human child = new Human(childName, "", childBirthYear);
        familyService.adoptChild(family, child);
        logger.info("Child adopted.");
    }

    private void deleteAllChildrenOlderThan() {
        int age = getUserInput("Enter the age limit: ");
        familyService.deleteChildrenOlderThan(age);
        logger.info("All children older than the specified age have been removed.");
    }
}
