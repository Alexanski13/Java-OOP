package ReflectionAndAnnotationExercise.BarracksWars.data;

import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Repository;
import ReflectionAndAnnotationExercise.BarracksWars.interfaces.Unit;
import jdk.jshell.spi.ExecutionControl;

import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

    private final Map<String, Integer> amountOfUnits;

    public UnitRepository() {
        this.amountOfUnits = new TreeMap<>();
    }

    public void addUnit(Unit unit) {
        final String unitType = unit.getClass().getSimpleName();
        if (!this.amountOfUnits.containsKey(unitType)) {
            this.amountOfUnits.put(unitType, 0);
        }

        final int newAmount = this.amountOfUnits.get(unitType) + 1;
        this.amountOfUnits.put(unitType, newAmount);
    }

    public String getStatistics() {
        final StringBuilder statBuilder = new StringBuilder();

        amountOfUnits.forEach((key, value) -> statBuilder.append(String.format("%s -> %d%n", key, value)));

        statBuilder.setLength(statBuilder.length() - System.lineSeparator().length());

        return statBuilder.toString();
    }

    public void removeUnit(String unitType) throws ExecutionControl.NotImplementedException {
        if (this.amountOfUnits.containsKey(unitType) && this.amountOfUnits.get(unitType) != 0) {
            final Integer amountOfUnits = this.amountOfUnits.get(unitType);
            this.amountOfUnits.put(unitType, amountOfUnits - 1);
        } else {
            throw new ExecutionControl.NotImplementedException("No such units in repository.");
        }
    }

}
