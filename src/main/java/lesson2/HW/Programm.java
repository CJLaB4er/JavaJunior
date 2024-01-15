package lesson2.HW;
/*
Создайте абстрактный класс "Animal" с полями "name" и "age".
Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
Выведите на экран информацию о каждом объекте.
Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
*/

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Programm {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Animal[] animals = new Animal[]{
                new Dog("Бим", 3, "Белый"),
                new Dog("Альма", 2, "Рыжая"),
                new Dog("Мухтар", 5, "Чёрный"),
                new Cat("Мурзик", 1, "Ласковый"),
                new Cat("Рыжик", 2, "Злой")
        };


        for (Animal animal :
                animals) {

            Class<?> clazz = animal.getClass();
            // Получение полей родительского класса и текущего класса
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            Field[] fields = clazz.getDeclaredFields();
            Field[] allFields = new Field[superFields.length + fields.length];

            System.arraycopy(superFields, 0, allFields, 0, superFields.length);
            System.arraycopy(fields, 0, allFields, superFields.length, fields.length);

            // Вывод информации о животном
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[")
                    .append("Class: ")
                    .append(clazz.getSimpleName())
                    .append(", ");

            for (Field field :
                    allFields) {
                field.setAccessible(true);
                stringBuilder
                        .append(field.getName())
                        .append(": ")
                        .append(field.get(animal))
                        .append(", ")
                ;
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append("] ");
            System.out.print(stringBuilder.toString());


            try {
                Method method = clazz.getMethod("makeSound");
                method.invoke(animal);
            } catch (NoSuchMethodException e) {
//                throw new RuntimeException(e);
            }
        }


    }
}
