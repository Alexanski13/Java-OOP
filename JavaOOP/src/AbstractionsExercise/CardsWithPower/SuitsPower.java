package AbstractionsExercise.CardsWithPower;

public enum SuitsPower {
    CLUBS(0),
    DIAMONDS(13),
    HEARTS(26),
    SPADES(39);

    private int suitPower;

    SuitsPower(int suitPower) {
        this.suitPower = suitPower;
    }

    public int getSuitPower() {
        return suitPower;
    }
}
