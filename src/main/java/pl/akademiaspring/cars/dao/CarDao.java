package pl.akademiaspring.cars.dao;

import pl.akademiaspring.cars.model.Car;

import java.util.List;

public interface CarDao {

    void createTable();

    List<Car> getCars();

    void addNewCar(Car car);

    List<Car> getCarsFromRange(long form, long to);


}
