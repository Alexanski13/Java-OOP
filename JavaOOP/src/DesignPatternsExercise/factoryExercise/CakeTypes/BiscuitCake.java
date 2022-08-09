package DesignPatternsExercise.factoryExercise.CakeTypes;

import DesignPatternsExercise.factoryExercise.Cake;

public class BiscuitCake extends Cake {
    public BiscuitCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Biscuit cake!");
    }

    @Override
    public void bake() {
        System.out.println("Baking Biscuit cake!");
    }

    @Override
    public void box() {
        System.out.println("Boxing Biscuit cake!");

    }
}
