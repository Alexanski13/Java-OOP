package ReflectionAndAnnotationExercise.BarracksWars.core.commands;

import ReflectionAndAnnotationExercise.BarracksWars.Annotations.Inject;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Repository;

public class Report extends Command {
    @Inject
    private Repository repository;

    protected Report(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }
}
