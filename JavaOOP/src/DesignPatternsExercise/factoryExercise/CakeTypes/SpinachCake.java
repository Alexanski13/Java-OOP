package DesignPatternsExercise.factoryExercise.CakeTypes;

import DesignPatternsExercise.factoryExercise.Cake;

public class SpinachCake extends Cake {
    public SpinachCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Spinach cake!");
    }

    @Override
    public void bake() {
        System.out.println("Baking Spinach cake!");
    }

    @Override
    public void box() {
        System.out.println("Boxing Spinach cake!");
    }
}
