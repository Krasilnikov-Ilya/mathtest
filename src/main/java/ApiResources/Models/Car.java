package ApiResources.Models;

import java.util.Objects;

/**
 * Модель автомобиля для создания списков из SQL и API ответов
 * Содержит переменные автомобиля, методы их получения и переопределённые методы проверок
 */

public class Car {

    // переменные для модели автомобиля
    private long id;
    private String engineType;
    private String mark;
    private String model;
    private double price;

    // модель автомобиля
    public Car(long id, String engineType,
               String mark, String model,
               double price) {
        this.id = id;
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    // набор методов получения переменных
    public long getId() {
        return id;
    }

    public String getEngineType() {
        return engineType;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    // метод для вывода данных в String формате
    @Override
    public String toString() {
        return "\n" + "Car { id:" + id + " engineType: " + engineType + " mark: " + mark + "\n"
                + " model: " + model + " price: " + price + " }";
    }

    // метод для сравнения данных двух автомобилей
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(car.price, price) == 0 && Objects.equals(engineType, car.engineType) && Objects.equals( mark, car. mark) && Objects.equals(model, car.model);
    }
}
