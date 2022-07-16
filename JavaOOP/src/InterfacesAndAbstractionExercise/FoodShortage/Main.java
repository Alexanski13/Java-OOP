package InterfacesAndAbstractionExercise.FoodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String, Buyer> buyers = new HashMap<>();

        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");

            if (tokens.length == 4) {
                String citizenName = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String id = tokens[2];
                String birthDate = tokens[3];
                Buyer citizen = new Citizen(citizenName, age, id, birthDate);
                buyers.put(citizenName, citizen);
            } else {
                String rebelName = tokens[0];
                int age = Integer.parseInt(tokens[1]);
                String group = tokens[2];
                Buyer rebel = new Rebel(rebelName, age, group);
                buyers.put(rebelName, rebel);
            }
        }

        String name = scanner.nextLine();

        while (!"End".equals(name)) {
            Buyer buyer = buyers.get(name);

            if (buyer != null) {
                buyer.buyFood();
            }


            name = scanner.nextLine();
        }

        System.out.println(buyers.values().stream()
                .mapToInt(Buyer::getFood)
                .sum());
    }
}
