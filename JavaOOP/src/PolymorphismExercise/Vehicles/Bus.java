package PolymorphismExercise.Vehicles;

public class Bus extends VehicleImpl {
    private boolean empty;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    @Override
    public String drive(double kilometers) {
        if (empty) {
            return "Bus " + super.drive(kilometers);
        } else {
            setFuelConsumption(getFuelConsumption() + 1.4);
            String result = "Bus " + super.drive(kilometers);
            setFuelConsumption(getFuelConsumption() - 1.4);
            return result;
        }
    }

    @Override
    public void printFuelQuantity() {
        System.out.print("Bus");
        super.printFuelQuantity();
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }
}
