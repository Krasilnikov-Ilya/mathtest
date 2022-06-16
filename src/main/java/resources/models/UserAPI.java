package resources.models;

/**
 * И всё это только для того, что бы сделать список пользователей
 * из одномерного массива, который выдается ответом сайта.
 */

public class UserAPI {
    // объявляю переменные для модели пользователя
    private int id;
    private String firstName;
    private String secondName;
    private int age;
    private String sex;
    private double money;

    // создаю модель "пользователь"
    public UserAPI(int id, String firstName,
                   String secondName, int age,
                   String sex, double money) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.sex = sex;
        this.money = money;
    }

    // здесь будет набор методов получения переменных
    // специально пишу в линию, иначе плохо читается
    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getSecondName() {return secondName;}
    public int getAge() {return age;}
    public String getSex() {return sex;} // смешно
    public double getMoney() {return money;}

    // здесь будет метод для вывода пользователя в String формате
    @Override // без переопределения снова не обошлось
    public String toString() {
        // я трачу больше времени на долбежку по клавиатуре, чем на реальную работу :(
        // P.S.: знаю, сам виноват.
        return "\n" + "User { id:" + id + " firstName: " + firstName + " secondName: " + secondName + "\n"
                + " age: " + age + " sex: " + sex + " money: " + money + " }";
    }
}
