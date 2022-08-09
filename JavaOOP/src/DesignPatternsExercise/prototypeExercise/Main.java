package DesignPatternsExercise.prototypeExercise;

public class Main {
    public static void main(String[] args) {
        EmployeeRecord alex = new EmployeeRecord(13, "Alex", "Sea", 5000, "Sofia");

        Prototype alexClone = alex.getClone();

        alex.showRecord();
    }
}
