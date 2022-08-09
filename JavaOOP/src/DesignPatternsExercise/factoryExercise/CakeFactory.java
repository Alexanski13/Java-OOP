package DesignPatternsExercise.factoryExercise;

import DesignPatternsExercise.factoryExercise.CakeTypes.BiscuitCake;
import DesignPatternsExercise.factoryExercise.CakeTypes.ChocolateCake;
import DesignPatternsExercise.factoryExercise.CakeTypes.SpinachCake;
import DesignPatternsExercise.factoryExercise.CakeTypes.WhiteCake;

public class CakeFactory {
    public static Cake createCake(String type) {
        Cake cake = null;
        switch (type) {
            case "Spinach":
                cake = new SpinachCake(15, 13, 20);
                break;
            case "Chocolate":
                cake = new ChocolateCake(15, 13, 20);
                break;
            case "White":
                cake = new WhiteCake(15, 13, 20);
                break;
            case "Biscuit":
                cake = new BiscuitCake(15, 13, 20);
                break;
        }
        return cake;
    }
}
