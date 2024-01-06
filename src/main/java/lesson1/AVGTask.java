package lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;

public class AVGTask {
    /*
    Напишите программу, которая использует Stream API для обработки списка чисел.
    Программа должна вывести на экран среднее значение всех четных чисел в списке.
 */


    public static void main(String[] args) {

        // Количество элементов в списке
        int count = 100;
        Random random = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            list.add(random.nextInt(100));
        }
        OptionalDouble avg = list.stream()
                .filter(n -> n % 2 == 0)
                .mapToDouble(n -> n)
                .average();

        if (avg.isPresent()) {
            System.out.printf("Среднее арифметическое чисел списка: %.2f", avg.getAsDouble());
        } else System.out.println("Спискок не содержит чётных чисел.");

    }
}
