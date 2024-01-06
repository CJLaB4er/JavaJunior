package lesson1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        example1();
//        examle2();
//        example3();
        example4();
    }

    private static void example1() {
//        PlainInterface plainInterface = new PlainInterface() {
//            @Override
//            public String action(int x, int y) {
//                return String.valueOf(x + y);
//            }
//        };

        PlainInterface plainInterface = Integer::sum;

        PlainInterface plainInterface1 = Integer::compare;

        System.out.println(plainInterface.action(5, 3));
        System.out.println(plainInterface1.action(1, 5));
    }

    private static void example2() {
        List<String> list = Arrays.asList("Привет", "мир", "!", "я", "родился", "!");
        list.stream().filter(str -> str.length() > 4).filter(str -> str.contains("о")).forEach(System.out::println);
    }

    private static void example3() {
        Arrays.asList(1, 10, 0, 7, 5, 5)
                .stream()
                .distinct()
                .sorted()
                .map(chislo -> "число: " + chislo + ". квадрат числа: " + chislo * chislo)
                .forEach(System.out::println);
    }

    private static void example4() {
        List<User> list = Arrays.asList(new User("Павел", 25),
                new User("Аркадий", 45),
                new User("Валера", 30));
        list
                .stream()
                .map(user -> new User(user.name, user.age - 5))
                .filter(user -> user.age >= 25)
                .forEach(System.out::println);
    }

    private static class User {
        String name;
        int age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

}
