package resources.models;

/**
 * И всё это только для того, что бы сделать список пользователей
 * из одномерного массива, который выдается ответом сайта.
 */

public class User {
    // объявляю переменные для модели пользователя
    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    // создаю модель "пользователь"
    public User(int id, String email,
                String first_name,
                String last_name, String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    // здесь будет набор методов получения переменных
    // специально пишу в линию, иначе плохо читается
    public int getId() {return id;}
    public String getEmail() {return email;}
    public String getFirst_name() {return first_name;}
    public String getLast_name() {return last_name;}
    public String getAvatar() {return avatar;}

    // здесь будет метод для вывода пользователя в String формате
    @Override // без переопределения снова не обошлось
    public String toString() {
        // я трачу больше времени на долбежку по клавиатуре, чем на реальную работу :(
        // P.S.: знаю, сам виноват.
        return "\n" + "User { id:" + id + " email: " + email + " first_name: " + first_name + " last_name: " + last_name + "\n" + " avatar_link: " + avatar + " }";
    }
}
