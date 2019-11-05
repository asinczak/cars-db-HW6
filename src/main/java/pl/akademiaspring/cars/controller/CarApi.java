package pl.akademiaspring.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiaspring.cars.dao.CarDao;
import pl.akademiaspring.cars.model.Car;
import pl.akademiaspring.cars.model.ProductionYear;
import pl.akademiaspring.cars.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarApi {

    private CarDao carDao;
    private CarService carService;
    private List<Car> carList;

    @Autowired
    public CarApi(CarDao carDao, CarService carService) {
        this.carList = new ArrayList<>();
        this.carDao = carDao;
        this.carService = carService;
    }

    @GetMapping("/cars")
    public String getCar(Model model){
        model.addAttribute("cars", carDao.getCars());
        model.addAttribute("newCar", new Car());
        model.addAttribute("productionYear", new ProductionYear());
        return "car";
    }

    @PostMapping("/carsList")
    public ModelAndView getCarsList() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("carList");
        mav.addObject("cars", carDao.getCars());
        return mav;
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car car) {
        car.setId(carService.getIdForNewCar());
        carDao.addNewCar(car);
        return "redirect:/cars";
    }

    @PostMapping("/carsListByProduction")
    public ModelAndView getCarsListByProduction(@ModelAttribute ProductionYear productionYear) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("carList");
        mav.addObject("cars", carDao.getCarsFromRange(productionYear.getFrom(), productionYear.getTo()));
        return mav;
    }

}
