

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static resources.Mathematic.NewSumOfManyInt;
import static resources.Mathematic.SumOfManyInt;

/**
 * Я создал ветки так как считаю необходимым разделить направления работы
 * Основное - ветка simplify, это план-минимум: JUnit5 + maven-surefire.
 * То, что не просили - ветка extend, в ней функционал фреймворка будет расширяться.
 */
public class MathTest {

    @Test
    public void TestSumOfAll1() {
        int[] question1 = {1,2,3,4,5,5,6,7,8,9}; //создаю массив 1
        int result1 = SumOfManyInt(question1); //применяю метод
        System.out.println(result1);//вывожу число
        Assertions.assertEquals(50, result1);//проверка результата
    }

    @Test
    public void TestSumOfAll2() {
        int[] question2 = {1,1,1,1,1}; //создаю массив 2
        int result2 = NewSumOfManyInt(question2); //применяю второй метод
        System.out.println(result2);//вывожу число
        Assertions.assertEquals(5, result2);//проверка результата
    }
}
