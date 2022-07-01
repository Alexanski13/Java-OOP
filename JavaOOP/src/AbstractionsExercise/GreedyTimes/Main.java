
package AbstractionsExercise.GreedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long entry = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        var bag = new LinkedHashMap<String, LinkedHashMap<String, Long>>();
        long gold = 0;
        long rocks = 0;
        long money = 0;

        for (int i = 0; i < safe.length; i += 2) {
            String name = safe[i];
            long count = Long.parseLong(safe[i + 1]);

            String result = "";

            if (name.length() == 3) {
                result = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                result = "Gem";
            } else if (name.toLowerCase().equals("gold")) {
                result = "Gold";
            }

            if (result.equals("")) {
                continue;
            } else if (entry < bag.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + count) {
                continue;
            }

            switch (result) {
                case "Gem":
                    if (!bag.containsKey(result)) {
                        if (bag.containsKey("Gold")) {
                            if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(result).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(result)) {
                        if (bag.containsKey("Gem")) {
                            if (count > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(result).values().stream().mapToLong(e -> e).sum() + count > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!bag.containsKey(result)) {
                bag.put((result), new LinkedHashMap<>());
            }

            if (!bag.get(result).containsKey(name)) {
                bag.get(result).put(name, 0L);
            }


            bag.get(result).put(name, bag.get(result).get(name) + count);
            if (result.equals("Gold")) {
                gold += count;
            } else if (result.equals("Gem")) {
                rocks += count;
            } else if (result.equals("Cash")) {
                money += count;
            }
        }

        for (var x : bag.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream()
                    .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey()))
                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}