import controller.FamilyController;
import dao.impl.CollectionFamilyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.FamilyService;

import java.util.Scanner;

public class HappyFamilyApp {

    private static final Logger logger = LoggerFactory.getLogger(HappyFamilyApp.class);

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

        String filePath = args.length > 0 ? args[0] : "C:/data/families_data.txt";

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            logger.info("1. View all families");
            logger.info("2. Save data to file");
            logger.info("3. Load data from file");
            logger.info("4. Exit");
            logger.info("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    familyService.getAllFamilies().forEach(family -> logger.info(family.toString()));
                    break;
                case 2:
                    logger.info("Saving data to: {}", filePath);
                    familyService.saveDataToFile(filePath);
                    break;
                case 3:
                    logger.info("Loading data from: {}", filePath);
                    familyService.loadDataFromFile(filePath);
                    break;
                case 4:
                    logger.info("Exiting...");
                    running = false;
                    break;
                default:
                    logger.warn("Invalid choice. Please try again.");
            }

            if (running) {
                logger.info("Do you want to continue? (yes/no): ");
                String continueChoice = scanner.nextLine().trim().toLowerCase();
                if (continueChoice.equals("no") || continueChoice.equals("n")) {
                    running = false;
                    logger.info("Exiting...");
                }
            }
        }
    }

}
