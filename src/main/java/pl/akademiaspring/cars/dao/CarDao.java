package pl.akademiaspring.cars.dao;

import pl.akademiaspring.cars.model.Car;

import java.util.List;

public interface CarDao {

    List<Car> getCars();

    void addNewCar(Car car);

    List<Car> getCarsFromRange(long form, long to);
}
