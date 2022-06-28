package ApiResources.JdbcUtilsNew;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * В данном классе реализована обработка результата выполнения запроса.
 * Класс сохраняет артефакты обработки запроса в приватных переменных, для независимости от подключения
 *
 * Полученный ответ (resultSet) используется для получения названий колонок и их количества.
 * Полученные данные используются для создания JSON массива.
 * Полученный массив переводится в Srting формат для изменения содержимого через replaceAll,
 * В том числе: замены названия имени и фамилии, а так же замена значений пола.
 * Подготовленные таким образом данные используются для создания результирующего массива.
 *
 * Так как в данном проекте не предусмотрено второй модели, вариации замены данных не реализованы.
 * В случае реализации второй модели возможно создание switch-case, использующего класс модели из теста,
 * Зоной ответственности которого будет выбор паттерна замены String данных для конкретной модели
 * Такая реализация обеспечит один "источник" изменения действий в реализации тестов: класс модели для Generics.
 */

public class ResultSetStringWrapper {
    private ResultSet resultSet;
    private String resultSetString;
    private ResultSetMetaData metaData;
    private int columnCount;
    private List<String> colNames;
    private JSONArray jsonArray;

    // сохранение результата запроса
    public void getResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    // получение характеристик результата запроса
    public void getMetaData() {
        try {
            metaData = resultSet.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("MetaData received");
    }

    // получение количества колонок из характеристик результата запроса
    public void getColumnCount() {
        try {
            columnCount = metaData.getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Total columns in result set: " + columnCount);
    }

    // получение названий колонок из характеристик результата запроса
    public void getColNames() {
        // полученное ранее количество колонок ограничивает количество имён
        colNames = IntStream.range(0, columnCount)
                .mapToObj(i -> {
                    try {
                        return metaData.getColumnName(i + 1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // в случае возникновения ошибки имя заполняется знаком вопроса
                        return "?";
                    }
                })
                .collect(Collectors.toList());
        System.out.println("Column names in result set: " + colNames);
    }

    public void convertResultSetToJsonArray() {
        // инициализация массива
        JSONArray result = new JSONArray();
        // условие цикла: пока есть данные, цикл их извлекает.
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // промежуточный объект: JSONObject. Хранит название поля и его содержимое
            JSONObject row = new JSONObject();
            colNames.forEach(columnName -> {
                try {
                    // такой подход не может быть реализован через Gson
                    row.put(columnName, resultSet.getObject(columnName));
                    // так как Gson требует приведения типов
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
            // добавление промежуточных объектов в массив
            result.put(row);
        }
        // сохранение конечного состояния массива в поле класса
        jsonArray = result;
    }

    // приведение массива к String формату для замены данных.
    public void convertJsonArrayToString() {
        resultSetString = jsonArray.toString();
    }

    // замена String данных
    public void replaceUserColumnsInString() {
        System.out.println("BEFORE REPLACING: " + resultSetString);
        resultSetString = resultSetString.replaceAll("first_name","firstName");
        resultSetString = resultSetString.replaceAll("second_name","secondName");
        resultSetString = resultSetString.replaceAll("true","MALE");
        resultSetString = resultSetString.replaceAll("false","FEMALE");
        System.out.println("AFTER REPLACING: " + resultSetString);
    }

    // создание результирующего списка для передачи в тест
    public <T> List<T> getListOfGenericTypeFromString (Class tClass) {
        // подключение библиотеки Gson
        Gson gson = new Gson();
        // создание списка
        Class<T[]> tArrayClass = (Class<T[]>) Array.newInstance(tClass,0).getClass();
        // передача списка
        return Arrays.stream(gson.fromJson(resultSetString, tArrayClass)).toList();
    }
}
