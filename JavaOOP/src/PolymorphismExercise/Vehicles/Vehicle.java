package PolymorphismExercise.Vehicles;

public interface Vehicle {
    String drive(double kilometers);
    void refuel(double liters);

    void printFuelQuantity();
}
