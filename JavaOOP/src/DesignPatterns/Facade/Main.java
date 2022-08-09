package DesignPatterns.Facade;

public class Main {
    public static void main(String[] args) {
        Car car = new CarBuilderFacade()
                .info()
                .withType("Nissan")
                .withColor("Black")
                .withNumberOfDoors(3)
                .built()
                .inCity("Kyoto")
                .atAddress("Kyoto street 7498")
                .build();

        System.out.println(car);
    }
}
