package hw01;

public class Human implements HumanActions {
    private String name;
    private String surname;
    private Integer dateOfBirthYear;
    private Integer IQ;
    private Pet pet;
    private Human mother;
    private Human father;
    private String[][] schedule; //2d array: [day of the week] x [type of the activity]

    public Human(String name, String surname, Integer dateOfBirthYear, Integer IQ, Pet pet, Human mother, Human father, String[][] schedule) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        this.IQ = IQ;
        this.pet = pet;
        this.mother = mother;
        this.father = father;
        this.schedule = schedule;
    }

    public Human() {
    }

    public Human(String name, String surname, Integer dateOfBirthYear) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public Human(String name, String surname, Integer dateOfBirthYear, Integer IQ, Pet pet, Human mother, Human father) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthYear = dateOfBirthYear;
        setIQ(IQ);
        this.pet = pet;
        this.mother = mother;
        this.father = father;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDateOfBirthYear() {
        return dateOfBirthYear;
    }

    public void setDateOfBirthYear(Integer dateOfBirthYear) {
        this.dateOfBirthYear = dateOfBirthYear;
    }

    public Integer getIQ() {
        return IQ;
    }

    public void setIQ(Integer IQ) {
        if (IQ == null) {
            this.IQ = null;
        } else if (IQ >= 1 && IQ <= 100) {
            this.IQ = IQ;
        } else {
            throw new IllegalArgumentException("IQ must be between 1 and 100, or null.");
        }
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        this.mother = mother;
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        this.father = father;
    }

    @Override
    public void greetPet() {
        System.out.println("Hello, " + pet.getNickname());
    }

    @Override
    public void describePet() {
        if (pet.getTrickLevel() > 50) {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is very sly.");
        } else {
            System.out.println("I have an " + pet.getSpecies() + " is " + pet.getAge() + " years old, he is almost not sly.");
        }

    }
}
