package resources.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersPageRootSQL {
    // список из моделей пользователей
    @SerializedName("data") // назван этот массив в массиве как "data"
    private List<User> user; // но я хочу что бы код читался.

    // методы для получения значений из Json
    public List<User> getUsersList() {return user;}

    // методы для внесения значений из Json
    public void setUsersList(List<User> user) {this.user = user;}

    @Override // обычный toString тут не катит, делаю кастомный
    public String toString() {
        return "Users page: users_array: " + user + "\n" + "}";
    }
}
