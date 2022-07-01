package AbstractionsExercise.CardRank;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Card Ranks:");
        Arrays.stream(CardRanks.values())
                .forEach(cardSuit -> System.out.printf("Ordinal value: %d; Name value: %s%n", cardSuit.ordinal(), cardSuit.name()));
    }
}
