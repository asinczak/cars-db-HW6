package pl.akademiaspring.cars.service;

import org.springframework.stereotype.Service;
import pl.akademiaspring.cars.dao.CarDao;
import pl.akademiaspring.cars.model.Car;

import java.util.List;

@Service
public class CarService {

    private CarDao carDao;

    public CarService(CarDao carDao) {
        this.carDao = carDao;
    }

    public long getIdForNewCar(){
        List<Car> carList = carDao.getCars();
        long id = 1;
        if(carList.isEmpty()){
            return id;
        } else {
            for (Car car : carList) {
                if(car.getId() > id){
                    id = car.getId();
                }
            }
            return id + 1L;
        }

    }
}
