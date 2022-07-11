package InterfacesAndAbstraction.BorderControl;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Identifiable> identifiables = new LinkedList<>();

        String command = scanner.nextLine();

        while (!"End".equals(command)) {
            String[] commandInfo = command.split("\\s+");
            if (commandInfo.length == 2) {
                String model = commandInfo[0];
                String id = commandInfo[1];
                Identifiable robot = new Robot(model, id);
                identifiables.add(robot);
            } else {
                String name = commandInfo[0];
                int age = Integer.parseInt(commandInfo[1]);
                String id = commandInfo[2];
                Identifiable citizen = new Citizen(name, age, id);
                identifiables.add(citizen);
            }

            command = scanner.nextLine();
        }

        String fakeIdPostFix = scanner.nextLine();

        identifiables.stream()
                .map(Identifiable::getId)
                .filter(i -> i.endsWith(fakeIdPostFix))
                .forEach(System.out::println);
    }
}
