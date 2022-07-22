package ReflectionAndAnnotationExercise.BarracksWars;

import ReflectionAndAnnotationExercise.BarracksWars.core.CommandInterpreterImpl;
import ReflectionAndAnnotationExercise.BarracksWars.core.Engine;
import ReflectionAndAnnotationExercise.BarracksWars.core.factories.UnitFactoryImpl;
import ReflectionAndAnnotationExercise.BarracksWars.data.UnitRepository;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {

        final Repository repository = new UnitRepository();

        final UnitFactory unitFactory = new UnitFactoryImpl();

        final CommandInterpreterImpl commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);

        final Engine engine = new Engine(commandInterpreter);

        engine.run();
    }

}
