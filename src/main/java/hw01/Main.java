package hw01;

import hw02.Family;

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


                Pet dogRock = new Pet(Species.DOG, "Rock", 5, 60, new String[]{"eat", "drink", "sleep"});
                Pet dogGuffy = new Pet(Species.DOG, "Guffy");


                fatherJon.setPet(dogGuffy);
                fatherJon.greetPet();
                dogGuffy.eat();
                dogGuffy.foul();

                System.out.println(fatherJon);
                System.out.println(dogGuffy);
                System.out.println("-------------------------");

                childMichael.setPet(dogRock);
                childMichael.greetPet();
                childMichael.describePet();

                dogRock.eat();
                dogRock.foul();

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
    }
}
