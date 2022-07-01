package AbstractionsExercise.CardsWithPower;

public class Card {
    private SuitsPower suitsPower;
    private RankPowers rankPowers;

    public Card(SuitsPower suitsPower, RankPowers rankPowers) {
        this.suitsPower = suitsPower;
        this.rankPowers = rankPowers;
    }

    public SuitsPower getSuitsPower() {
        return suitsPower;
    }

    public RankPowers getRankPowers() {
        return rankPowers;
    }

    public int getPower() {
        return rankPowers.getRankPower() + suitsPower.getSuitPower();
    }
}
