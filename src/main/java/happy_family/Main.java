package happy_family;

import dao_layer.controller.FamilyController;
import dao_layer.dao.CollectionFamilyDao;
import dao_layer.service.FamilyService;

public class Main {

    public static void main(String[] args) {
        int startCount = 10_000;
        int endCount = 10_000_000;

        for (int count = startCount; count <= endCount; count *= 10) {
            System.out.println("Creating " + count + " Human objects...");

            for (int i = 0; i < count; i++) {
                Human fatherJon = new Human("Jon", "Potter", 315532800000L); // 1970
                Human motherJane = new Human("Jane", "Potter", 157766400000L); // 1975
                Family family = new Family(motherJane, fatherJon);
                Human childMichael = new Human("Michael", "Potter", 852076800000L, family); // 1997

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

        Human mother1 = new Human("Maria", "Blamberg", 252460800000L);
        Human father1 = new Human("John", "Doe", 225206400000L);
        controller.createNewFamily(mother1, father1);

        Human mother2 = new Human("Anna", "Smith", 473385600000L);
        Human father2 = new Human("Michael", "Johnson", 452390400000L);
        controller.createNewFamily(mother2, father2);

        Human mother3 = new Human("Emily", "Davis", 631152000000L);
        Human father3 = new Human("Robert", "Brown", 599616000000L);
        controller.createNewFamily(mother3, father3);

        controller.getFamilyById(0).addChild(new Human("Alice", "Doe", 1262304000000L));
        controller.getFamilyById(0).addChild(new Human("Bob", "Doe", 1325376000000L));
        controller.getFamilyById(1).addChild(new Human("Chris", "Smith", 1420070400000L));

        System.out.println("All Families:");
        controller.displayAllFamilies();

        System.out.println("\nFamilies with two or fewer members:");
        controller.getFamiliesLessThan(3).forEach(System.out::println);

        System.out.println("\nFamilies with exactly 2 members:");
        System.out.println(controller.countFamiliesWithMemberNumber(2));

        controller.getAllFamilies().stream()
                .filter(family -> family.countFamily() == 2)
                .forEach(System.out::println);

        System.out.println("\nFamilies with more than three members:");
        controller.getFamiliesBiggerThan(3).forEach(System.out::println);

        Family familyToAddChild = controller.getFamilyById(0);
        controller.bornChild(familyToAddChild, "Charlie", "Ella");

        System.out.println("\nUpdated family information for Family 1:");
        System.out.println(controller.getFamilyById(0));

        System.out.println("\nDeleting children older than 2 years:");
        controller.deleteAllChildrenOlderThen(2);

        System.out.println("\nFamily information:");
        controller.displayAllFamilies();

        System.out.println("\nTotal number of families: " + controller.count());
    }

}
