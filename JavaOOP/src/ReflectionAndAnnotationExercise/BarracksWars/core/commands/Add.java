package ReflectionAndAnnotationExercise.BarracksWars.core.commands;

import ReflectionAndAnnotationExercise.BarracksWars.Annotations.Inject;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Unit;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

public class Add extends Command {
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;

    protected Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException,
            ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        final String unitType = super.getData()[1];

        final Unit unitToAdd = this.unitFactory.createUnit(unitType);

        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }
}
