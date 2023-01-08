package be.helb.service;

import be.helb.DAO.CarDao;
import be.helb.DAO.UserDao;
import be.helb.model.Car;
import be.helb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarShopService {
    @Autowired
    private CarDao carDao;
    @Autowired
    private UserDao userDao;
    public CarShopService(CarDao carDao,UserDao userDao) {
        this.carDao = carDao;
        this.userDao=userDao;
    }

    //afficher et lister des données en json
    public List<Car> getAllCar() {
        return carDao.findAll();
    }

    //Poster de nouvelles données
    public Car saveNewCar(Car car) {
        return carDao.save(car);
    }


    //Updater des données existante
    public Car update(Long id, Car car) {
        Car carToUpdate = carDao.findById(id).get();
        //Mettre à jour les champs souhaités
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setModel(car.getModel());
        carToUpdate.setYear(car.getYear());
        carToUpdate.setPrice(car.getPrice());
        return carDao.save(carToUpdate);
    }

    // fonction qui affiche des résultat en se basant sur des préférence de l’utilisateurs
    public List<Car> searchCars(String brand, String model) {
        return carDao.findByBrandAndModel(brand, model);
    }
}
