package ReflectionAndAnnotationExercise.BarracksWars.core.commands;

import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Executable;

public abstract class Command implements Executable {

    private final String[] data;


    protected Command(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return this.data;
    }
}

