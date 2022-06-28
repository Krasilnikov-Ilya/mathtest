package ApiResources.JdbcUtilsNew;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
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

    public JSONArray convertResultSetToJsonArray() {
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
        return jsonArray;
    }

    // приведение массива к String формату.
    public void convertJsonArrayToString() {
        resultSetString = jsonArray.toString();
        System.out.println("String Json data: " + resultSetString);
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
