package resources.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Итак, содержимое Json нужно будет переводить в объектную модель
 * Модель для страницы списка пользователей представлена в этом классе
 * Она использует модель пользователя, лежащую так же в папке ресурсов
 */

// модель Json из ответа
public class UsersPageRoot {
    // объявляю переменные для модели
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    // список из моделей пользователей
    @SerializedName("data") // назван этот массив в массиве как "data"
    private List<User> user; // но я хочу что бы код читался.

    // методы для получения значений из Json
    // все в строчку, ибо не читается
    public int getPage(int page) {return page;}
    public int getPer_Page(int per_page) {return per_page;}
    public int getTotal(int total) {return total;}
    public int getTotal_Pages(int total_pages) {return total_pages;}
    public List<User> getUsersList() {return user;}

    // методы для внесения значений из Json
    public void setPage(int page) {this.page = page;}
    public void setPer_Page(int per_page) {this.per_page = per_page;}
    public void setTotal(int total) {this.total = total;}
    public void setTotal_Pages(int total_pages) {this.total_pages = total_pages;}
    public void setUsersList(List<User> user) {this.user = user;}

    @Override // обычный toString тут не катит, делаю кастомный
    public String toString() {
        // прописать это - та еще задачка
        return "Users page: " + page + ", per_page: " + per_page +
                ", total: " + total + ", total_pages: " +
                total_pages + ", users_array: " + user + "\n" + "}";
    }






}
