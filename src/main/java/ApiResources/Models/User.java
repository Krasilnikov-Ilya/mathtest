package ApiResources.Models;

import java.util.Objects;

/**
 * Модель пользователя для создания списков из SQL и API ответов
 * Содержит переменные пользователя, методы их получения и переопределённые методы проверок
 */

public class User {

    // переменные для модели пользователя
    private long id;
    private String firstName;
    private String secondName;
    private int age;
    private String sex;
    private double money;

    // модель пользователя
    public User(long id, String firstName,
                String secondName, int age,
                String sex, double money) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    // набор методов получения переменных
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    } // смешно

    public double getMoney() {
        return money;
    }

    // метод для вывода данных в String формате
    @Override
    public String toString() {
        return "\n" + "User { id:" + id + " firstName: " + firstName + " secondName: " + secondName + "\n"
                + " age: " + age + " sex: " + sex + " money: " + money + " }";
    }

    // метод для сравнения данных двух пользователей
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Double.compare(user.money, money) == 0 && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(sex, user.sex);
    }
}
