package resources.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Итак, содержимое Json нужно будет переводить в объектную модель
 * Модель для страницы списка пользователей представлена в этом классе
 * Она использует модель пользователя, лежащую так же в папке ресурсов
 *
 * После смены сайта Serialized Name уже не нужен, ведь названия у массива нет
 * Но пусть будет, на память.
 */

// модель Json из ответа
public class UsersPageRootAPI {
    // список из моделей пользователей
    @SerializedName("data") // назван этот массив в массиве как "data"
    private List<UserAPI> userAPI; // но я хочу что бы код читался.

    // методы для получения значений из Json
    public List<UserAPI> getUsersList() {return userAPI;}

    // методы для внесения значений из Json
    public void setUsersList(List<UserAPI> userAPI) {this.userAPI = userAPI;}

    @Override // обычный toString тут не катит, делаю кастомный
    public String toString() {
        return "Users page: users_array: " + userAPI + "\n" + "}";
    }






}
