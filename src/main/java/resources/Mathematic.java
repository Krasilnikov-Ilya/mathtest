package resources;

/**
 * Здесь лежит класс для методов, в него вложены методы суммирования
 * Циклические методы суммирования принимают произвольное количество int значений
 * И возвращают int результат сложения значений
 */

public class Mathematic {
    public static int SumOfManyInt(int...numbers) {
        int summary = 0; //предварительное объявление результата
        for(int i = 0 ; i < numbers.length; i++) //переменная, условие, действие цикла
        {
            summary += numbers[i]; //циклическое увеличения результата
        }
        return summary; //возвращаемый результат
    }

    // Вариант с "enhanced for loop" мне уже ijIdea подсказала.
    public static int NewSumOfManyInt(int...numbers) {
        int summary = 0; //предварительное объявление результата
        for (int number : numbers) //переменная, условие, действие цикла в виде enhanced for loop
        {
            summary += number; //циклическое увеличения результата
        }
        return summary; //возвращаемый результат
    }
}