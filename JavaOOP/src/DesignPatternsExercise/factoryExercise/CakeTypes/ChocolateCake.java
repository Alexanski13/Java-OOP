package DesignPatternsExercise.factoryExercise.CakeTypes;

import DesignPatternsExercise.factoryExercise.Cake;

public class ChocolateCake extends Cake {
    public ChocolateCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing Chocolate cake!");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chocolate cake!");
    }

    @Override
    public void box() {
        System.out.println("Boxing Chocolate cake!");
    }
}
