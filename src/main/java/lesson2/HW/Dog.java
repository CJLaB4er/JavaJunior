package lesson2.HW;

public class Dog extends Animal {
    private String color;

    public void makeSound() {
        System.out.println("Гав");
    }

    public Dog(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

}
