import org.junit.Assert;
import org.junit.Test;

import static resources.Mathematic.NewSumOfManyInt;
import static resources.Mathematic.SumOfManyInt;

/**
 * Итак, я решил сделать два теста, т.к. создал два метода:
 * SumOfManyInt использует for(), NewSumOfManyInt - enhanced for loop
 *
 * С пониманием задания у меня опять начались проблемы:
 * Строка "Для этого, написать в папке с ресурсами класс Mathematic"
 * Мне не понятна, т.к. папка src.main.resources не может вмещать класс
 * Я создал папку test.java.resources и закинул класс с методомами в неё.
 *
 * Задание я выполнил к 21 по Москве, остальное время будет потрачено
 * на добавление того, о чём меня не просили, например Allure
 * По этой причине коммита будет два.
 */
public class MathTest {

    @Test
    public void TestSumOfAll1() {
        int[] question1 = {1,2,3,4,5,5,6,7,8,9}; //создаю массив 1
        int result1 = SumOfManyInt(question1); //применяю метод
        System.out.println(result1);//вывожу число
        Assert.assertEquals(50, result1);//проверка результата
    }

    @Test
    public void TestSumOfAll2() {
        int[] question2 = {1,1,1,1,1}; //создаю массив 2
        int result2 = NewSumOfManyInt(question2); //применяю второй метод
        System.out.println(result2);//вывожу число
        Assert.assertEquals(5, result2);//проверка результата
    }
}
