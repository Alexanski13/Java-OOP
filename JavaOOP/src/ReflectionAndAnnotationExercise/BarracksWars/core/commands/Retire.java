package ReflectionAndAnnotationExercise.BarracksWars.core.commands;

import ReflectionAndAnnotationExercise.BarracksWars.Annotations.Inject;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;


    protected Retire(String[] data) {
        super(data);
    }


    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        final String unitType = super.getData()[1];
        this.repository.removeUnit(unitType);

        return unitType + " retired!";
    }
}
