package Inheritance.RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList list = new RandomArrayList();

        list.add(13);
        list.add(42);
        list.add(69);
        list.add(420);
        list.add(72);

        System.out.println(list.getRandomElement());
    }
}
