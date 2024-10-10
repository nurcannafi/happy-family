package hw01;

import hw02.Family;

public class Main {

    public static void main(String[] args) {
        int startCount = 10_000;
        int endCount = 10_000_000;

        for (int count = startCount; count <= endCount; count *= 10) {
            System.out.println("Creating " + count + " Human objects...");

            for (int i = 0; i < count; i++) {
                try (Human fatherJon = new Human("Jon", "Potter", 1970);
                     Human motherJane = new Human("Jane", "Potter", 1975);
                     Family family = new Family(motherJane, fatherJon);
                     Human childMichel = new Human("Michael", "Potter", 1997, family);
                     Pet dogRock = new Pet("dog", "Rock", 5, 60, new String[]{"eat", "drink", "sleep"});
                     Pet dogGuffy = new Pet("dog", "Guffy");
                     Human childHarry = new Human()) {

                    childHarry.setPet(dogGuffy);
                    childHarry.greetPet();
                    dogGuffy.eat();
                    dogGuffy.foul();

                    System.out.println(childHarry);
                    System.out.println(dogGuffy);
                    System.out.println("-------------------------");

                    childMichel.setPet(dogRock);
                    childMichel.greetPet();
                    childMichel.describePet();

                    dogRock.eat();
                    dogRock.foul();

                    System.out.println(childMichel);
                    System.out.println(dogRock);
                    System.out.println("-------------------------");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            System.gc();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
