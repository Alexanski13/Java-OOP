package InterfacesAndAbstractionExercise.BirthdayCelebrations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        List<Citizen> citizens = new ArrayList<>();
        List<Pet> pets = new ArrayList<>();

        while (!"End".equals(command)) {
            String[] commandInfo = command.split("\\s+");

            switch (commandInfo[0]) {
                case "Citizen":
                    String citizenName = commandInfo[1];
                    int age = Integer.parseInt(commandInfo[2]);
                    String id = commandInfo[3];
                    String birthDate = commandInfo[4];
                    Citizen citizen = new Citizen(citizenName, age, id, birthDate);
                    citizens.add(citizen);
                    break;
                case "Robot":
                    String model = commandInfo[1];
                    id = commandInfo[2];
                    Robot robot = new Robot(id, model);
                    break;
                case "Pet":
                    String petName = commandInfo[1];
                    String petBirthDate = commandInfo[2];
                    Pet pet = new Pet(petName, petBirthDate);
                    pets.add(pet);
                    break;
            }

            command = scanner.nextLine();
        }

        String year = scanner.nextLine();

        for (Citizen citizen : citizens) {
            if (citizen.getBirthDate().endsWith(year)) {
                System.out.println(citizen.getBirthDate());
            }
        }

        for (Pet pet : pets) {
            if (pet.getBirthDate().endsWith(year)) {
                System.out.println(pet.getBirthDate());
            }
        }
    }
}
