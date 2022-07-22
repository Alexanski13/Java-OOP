package ReflectionAndAnnotationExercise.BarracksWars.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data) throws ClassNotFoundException, NoSuchMethodException;
}
