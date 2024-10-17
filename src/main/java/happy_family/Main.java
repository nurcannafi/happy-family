package happy_family;

import dao_layer.controller.FamilyController;
import dao_layer.dao.CollectionFamilyDao;
import dao_layer.service.FamilyService;

public class Main {

    public static void main(String[] args) {
//        int startCount = 10_000;
//        int endCount = 10_000_000;
//
//        for (int count = startCount; count <= endCount; count *= 10) {
//            System.out.println("Creating " + count + " Human objects...");
//
//            for (int i = 0; i < count; i++) {
//                Human fatherJon = new Human("Jon", "Potter", 315532800000L); // 1970
//                Human motherJane = new Human("Jane", "Potter", 157766400000L); // 1975
//                Family family = new Family(motherJane, fatherJon);
//                Human childMichael = new Human("Michael", "Potter", 852076800000L, family); // 1997
//
//                fatherJon.addToSchedule(DayOfWeek.MONDAY, "Work");
//                fatherJon.addToSchedule(DayOfWeek.TUESDAY, "Gym");
//                fatherJon.addToSchedule(DayOfWeek.WEDNESDAY, "Meeting");
//
//                Pet dogRock = new Dog("Rock");
//                Pet dogGuffy = new Dog("Guffy");
//
//                fatherJon.setPet(dogGuffy);
//                fatherJon.greetPet();
//                dogGuffy.eat();
//
//                System.out.println(fatherJon);
//                System.out.println(dogGuffy);
//                System.out.println("-------------------------");
//
//                childMichael.setPet(dogRock);
//                childMichael.greetPet();
//                childMichael.describePet();
//
//                dogRock.eat();
//
//                System.out.println(childMichael);
//                System.out.println(dogRock);
//                System.out.println("-------------------------");
//
//                fatherJon = null;
//                motherJane = null;
//                family = null;
//                childMichael = null;
//                dogRock = null;
//                dogGuffy = null;
//            }
//
//            System.gc();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        CollectionFamilyDao familyDao = new CollectionFamilyDao();
        FamilyService familyService = new FamilyService(familyDao);
        familyService.fillWithTestData();
        FamilyController familyController = new FamilyController(familyService);
        familyController.start();
    }
}
