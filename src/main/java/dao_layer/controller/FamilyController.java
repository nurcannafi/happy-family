package dao_layer.controller;

import dao_layer.exception.FamilyOverFlowException;
import dao_layer.service.FamilyService;
import happy_family.Family;
import happy_family.Human;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FamilyController {

    private final FamilyService familyService;
    private final Scanner scanner;

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
                default -> System.out.println("Invalid command! Please select a valid command.");
            }
        }
    }

    public void showMainMenu() {
        System.out.println("\nChoose a command:");
        System.out.println("1. Fill with test data");
        System.out.println("2. Display the entire list of families");
        System.out.println("3. Display families bigger than a specified number");
        System.out.println("4. Display families less than a specified number");
        System.out.println("5. Count families with a specific number of members");
        System.out.println("6. Create a new family");
        System.out.println("7. Delete a family by index");
        System.out.println("8. Edit a family by index");
        System.out.println("9. Remove all children over the age of majority");
        System.out.println("10. Exit");
    }

    private int getUserInput(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void fillWithTestData() {
        familyService.fillWithTestData();
        System.out.println("Test data has been added.");
    }

    private void displayAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            System.out.println("The family list is empty.");
        } else {
            families.forEach(System.out::println);
        }
    }

    private void displayFamiliesBiggerThan() {
        int size = getUserInput("Enter the minimum family size: ");
        List<Family> families = familyService.displayFamiliesBiggerThan(size);
        if (families.isEmpty()) {
            System.out.println("No families found.");
        } else {
            families.forEach(System.out::println);
        }
    }

    private void displayFamiliesLessThan() {
        int size = getUserInput("Enter the maximum family size: ");
        List<Family> families = familyService.displayFamiliesLessThan(size);
        if (families.isEmpty()) {
            System.out.println("No families found.");
        } else {
            families.forEach(System.out::println);
        }
    }

    private void countFamiliesWithMembers() {
        int familyIndex = getUserInput("Enter the family index: ");
        Optional<Family> family = familyService.getFamilyByIndex(familyIndex);

        if (family.isPresent()) {
            int count = familyService.countFamilyMembers(family.get());
            System.out.println("Number of family members: " + count);
        } else {
            System.out.println("Invalid family index.");
        }
    }


    private void createNewFamily() {
        Human mother = getHumanDetails("mother");
        Human father = getHumanDetails("father");
        familyService.createNewFamily(mother, father);
        System.out.println("New family created.");
    }

    private Human getHumanDetails(String role) {
        System.out.println("Enter details for the " + role + ":");
        System.out.print("Name: ");
        scanner.nextLine();
        String name = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        long birthYear = getUserInput("Birth Year: ");
        return new Human(name, lastName, birthYear);
    }

    private void deleteFamilyByIndex() {
        int index = getUserInput("Enter the family index: ") - 1;
        familyService.deleteFamilyByIndex(index);
        System.out.println("Family deleted if the index was valid.");
    }

    private void editFamilyByIndex() {
        int index = getUserInput("Enter the family index: ") - 1;
        Family family = familyService.getFamilyByIndex(index).orElse(null);
        if (family == null) {
            System.out.println("Family not found.");
            return;
        }

        System.out.println("1. Give birth to a child");
        System.out.println("2. Adopt a child");
        int editChoice = getUserInput("Choose an option: ");

        switch (editChoice) {
            case 1 -> handleChildBirth(family);
            case 2 -> handleChildAdoption(family);
            default -> System.out.println("Invalid choice.");
        }
    }

    private void handleChildBirth(Family family) {
        if (family.countFamily() > 4) {
            throw new FamilyOverFlowException("Reached the maximum number of family members!");
        }
        System.out.print("Son's name: ");
        scanner.nextLine(); // Clear the buffer
        String maleName = scanner.nextLine();
        System.out.print("Daughter's name: ");
        String femaleName = scanner.nextLine();
        familyService.bornChild(family, maleName, femaleName);
        System.out.println("Child born.");
    }

    private void handleChildAdoption(Family family) {
        if (family.countFamily() > 4) {
            throw new FamilyOverFlowException("Reached the maximum number of family members!");
        }
        System.out.print("Child's name: ");
        scanner.nextLine();
        String childName = scanner.nextLine();
        long childBirthYear = getUserInput("Child's birth year: ");
        Human child = new Human(childName, "", childBirthYear);
        familyService.adoptChild(family, child);
        System.out.println("Child adopted.");
    }

    private void deleteAllChildrenOlderThan() {
        int age = getUserInput("Enter the age limit: ");
        familyService.deleteChildrenOlderThan(age);
        System.out.println("All children older than the specified age have been removed.");
    }
}
