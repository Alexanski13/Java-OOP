package solid.products;

public abstract class Drink {
    private final double milliliters;
    private final double density;

    public Drink(double milliliters, double density) {
        this.milliliters = milliliters;
        this.density = density;
    }

    public double getMilliliters() {
        return milliliters;
    }


    public double getDensity() {
        return density;
    }

    double getFoodAmountInKilograms() {
        return (1000 / this.getMilliliters()) * getDensity();
    }
}
