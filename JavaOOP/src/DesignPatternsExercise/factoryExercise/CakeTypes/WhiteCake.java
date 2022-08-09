package DesignPatternsExercise.factoryExercise.CakeTypes;

import DesignPatternsExercise.factoryExercise.Cake;

public class WhiteCake extends Cake {
    public WhiteCake(double diameter, double price, int pieces) {
        super(diameter, price, pieces);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing White cake!");
    }

    @Override
    public void bake() {
        System.out.println("Baking White cake!");
    }

    @Override
    public void box() {
        System.out.println("Boxing White cake!");
    }
}
