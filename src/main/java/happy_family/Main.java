package happy_family;

import dao_layer.controller.FamilyController;
import dao_layer.dao.CollectionFamilyDao;
import dao_layer.sevrice.FamilyService;

public class Main {

    public static void main(String[] args) {
        int startCount = 10_000;
        int endCount = 10_000_000;

        for (int count = startCount; count <= endCount; count *= 10) {
            System.out.println("Creating " + count + " Human objects...");

            for (int i = 0; i < count; i++) {
                Human fatherJon = new Human("Jon", "Potter", 1970);
                Human motherJane = new Human("Jane", "Potter", 1975);
                Family family = new Family(motherJane, fatherJon);
                Human childMichael = new Human("Michael", "Potter", 1997, family);


                fatherJon.addToSchedule(DayOfWeek.MONDAY, "Work");
                fatherJon.addToSchedule(DayOfWeek.TUESDAY, "Gym");
                fatherJon.addToSchedule(DayOfWeek.WEDNESDAY, "Meeting");


                Pet dogRock = new Dog("Rock");
                Pet dogGuffy = new Dog("Guffy");


                fatherJon.setPet(dogGuffy);
                fatherJon.greetPet();
                dogGuffy.eat();

                System.out.println(fatherJon);
                System.out.println(dogGuffy);
                System.out.println("-------------------------");

                childMichael.setPet(dogRock);
                childMichael.greetPet();
                childMichael.describePet();

                dogRock.eat();

                System.out.println(childMichael);
                System.out.println(dogRock);
                System.out.println("-------------------------");


                fatherJon = null;
                motherJane = null;
                family = null;
                childMichael = null;
                dogRock = null;
                dogGuffy = null;
            }


            System.gc();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FamilyController controller = new FamilyController(new FamilyService(new CollectionFamilyDao()));

        // Creating families
        Human mother1 = new Human("Maria", "Blamberg", 1978);
        Human father1 = new Human("John", "Doe", 1977);
        controller.createNewFamily(mother1, father1); // Family 1

        Human mother2 = new Human("Anna", "Smith", 1985);
        Human father2 = new Human("Michael", "Johnson", 1984);
        controller.createNewFamily(mother2, father2); // Family 2

        Human mother3 = new Human("Emily", "Davis", 1990);
        Human father3 = new Human("Robert", "Brown", 1989);
        controller.createNewFamily(mother3, father3); // Family 3

        // Adding children to families
        controller.getFamilyById(0).addChild(new Human("Alice", "Doe", 2010)); // Family 1
        controller.getFamilyById(0).addChild(new Human("Bob", "Doe", 2012)); // Family 1
        controller.getFamilyById(1).addChild(new Human("Chris", "Smith", 2015)); // Family 2
        // No children added to Family 3

        // Displaying family information
        System.out.println("All Families:");
        controller.displayAllFamilies();

        // Finding families that meet specific conditions
        System.out.println("\nFamilies with two or fewer members:");
        controller.getFamiliesLessThan(3).forEach(System.out::println);

        System.out.println("\nFamilies with exactly 2 members:");
        System.out.println(controller.countFamiliesWithMemberNumber(2));

        // Listing families with exactly 2 members
        controller.getAllFamilies().stream()
                .filter(family -> family.countFamily() == 2)
                .forEach(System.out::println);

        // Families with more than three members
        System.out.println("\nFamilies with more than three members:");
        controller.getFamiliesBiggerThan(3).forEach(System.out::println);

        // Child birth operation
        Family familyToAddChild = controller.getFamilyById(0);
        controller.bornChild(familyToAddChild, "Charlie", "Ella"); // Adding a new child to Family 1

        // Display updated family information
        System.out.println("\nUpdated family information for Family 1:");
        System.out.println(controller.getFamilyById(0));

        // Deleting children older than a certain age
        System.out.println("\nDeleting children older than 2 years:");
        controller.deleteAllChildrenOlderThen(2);

        // Display updated family information
        System.out.println("\nFamily information:");
        controller.displayAllFamilies();

        // Checking family count
        System.out.println("\nTotal number of families: " + controller.count());
    }
}
