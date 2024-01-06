package lesson1.task2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Корзина
 *
 * @param <T> Еда
 */
public class Cart<T extends Food> {

    //region Поля

    /**
     * Товары в магазине
     */
    private final ArrayList<T> foodstuffs;
    private final UMarket market;
    private final Class<T> clazz;

    //endregion

    //region Конструкторы

    /**
     * Создание нового экземпляра корзины
     *
     * @param market принадлежность к магазину
     */
    public Cart(Class<T> clazz, UMarket market) {
        this.clazz = clazz;
        this.market = market;
        foodstuffs = new ArrayList<>();
    }

    public void cardBalancing() {
        // По кол-ву элементов во множестве, будет определяться наличие всех элементов.
        Set<String> elements = new HashSet<>();

        // Проверяем товары в корзине на наличие элементов.
        foodstuffs.stream()
                .filter(elem -> elem.getProteins()
                        || elem.getFats()
                        || elem.getCarbohydrates())
                .forEach(elem -> {
                    if (elem.getProteins()) elements.add("prot");
                    if (elem.getFats()) elements.add("fats");
                    if (elem.getCarbohydrates()) elements.add("carb");
                });

        // Если число элементов во множестве равняется трём, то корзина сбалансирована
        if (elements.size() == 3) {
            System.out.println("Корзина уже сбалансирована по БЖУ.");
            return;
        }

        // Если корзина не сбалансирована, то ищем в магазине, и добаляем необходимые товары
        market.getThings(clazz).stream()
                .filter(thing -> thing.getProteins() || thing.getFats() || thing.getCarbohydrates())
                .forEach(thing -> {
                    if (!elements.contains("prot") && thing.getProteins()) {
                        elements.add("prot");
                        foodstuffs.add(thing);
                    } else if (!elements.contains("fats") && thing.getFats()) {
                        elements.add("fats");
                        foodstuffs.add(thing);
                    } else if (!elements.contains("carb") && thing.getCarbohydrates()) {
                        elements.add("carb");
                        foodstuffs.add(thing);
                    }
                });

        // Окончательная проверка корзины
        if (elements.size() == 3) {
            System.out.println("Корзина сбалансирована по БЖУ.");
        } else {
            System.out.println("Невозможно сбалансировать корзину по БЖУ.");
        }
    }

    //endregion

    public Collection<T> getFoodstuffs() {
        return foodstuffs;
    }


    public void printFoodstuffs() {
        /*int index = 1;
        for (var food : foodstuffs)
            System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n", index++, food.getName(), food.getProteins() ? "Да" : "Нет",
                    food.getFats() ? "Да" : "Нет", food.getCarbohydrates() ? "Да" : "Нет");*/


        AtomicInteger index = new AtomicInteger(1);
        foodstuffs.forEach(food -> System.out.printf("[%d] %s (Белки: %s Жиры: %s Углеводы: %s)\n",
                index.getAndIncrement(), food.getName(),
                food.getProteins() ? "Да" : "Нет",
                food.getFats() ? "Да" : "Нет",
                food.getCarbohydrates() ? "Да" : "Нет"));
    }
}
