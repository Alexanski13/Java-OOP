package AbstractionsExercise.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RankPowers rankPowers = RankPowers.valueOf(scanner.nextLine());
        SuitsPower suitsPower = SuitsPower.valueOf(scanner.nextLine());

        Card card = new Card(suitsPower, rankPowers);

        System.out.printf("Card name: %s of %s; Card power: %d", card.getRankPowers(), card.getSuitsPower(), card.getPower());
    }
}
