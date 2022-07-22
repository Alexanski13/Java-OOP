package ReflectionAndAnnotationExercise.BarracksWars.models.units;

public class Gunner extends AbstractUnit {

    private static final int GUNNER_HEALTH_AND_DAMAGE = 20;

    protected Gunner() {
        super(GUNNER_HEALTH_AND_DAMAGE, GUNNER_HEALTH_AND_DAMAGE);
    }
}
