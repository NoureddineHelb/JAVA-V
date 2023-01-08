package be.helb.service;

import be.helb.DAO.CarDao;
import be.helb.DAO.UserDao;
import be.helb.controller.controller;
import be.helb.model.Car;
import org.easymock.EasyMock;
import org.junit.jupiter.api.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarShopServiceTestExo {

    private CarDao carDao;
    private UserDao userDao;
    private CarShopService carShopService;

    @Test
    public void testGetAllCars(){
        List<Car> carList = List.of(new Car("volvo","xc60",2012,14500), new Car("ferrari", "412",2022, 430000));
        carDao = EasyMock.mock(CarDao.class);
        EasyMock.expect(carDao.findAll()).andReturn(carList);
        carShopService = new CarShopService(carDao,userDao);
        EasyMock.replay(carDao);
        List<Car> result = carShopService.getAllCar();
        EasyMock.verify(carDao);
        assertEquals(2, result.size());
    }

    @Test
    public void testSaveNewCar() {
        String name = "BMW";
        String model = "i30";
        int year = 2012;
        int price = 13000;
        Car car = new Car(name, model, year, price);
        CarDao carDao = EasyMock.mock(CarDao.class);
        EasyMock.expect(carDao.save(car)).andReturn(car);
        EasyMock.replay(carDao);
        CarShopService carShopService = new CarShopService(carDao, userDao);
        Car result = carShopService.saveNewCar(car);
        EasyMock.verify(carDao);
        assertEquals(car, result);
        EasyMock.verify(carDao);
    }

    @Test
    public void testUpdateCar() {
        Car car = new Car("audi", "rs7", 2015,43000);
        car.setId(1L);
        carDao = EasyMock.mock(CarDao.class);
        EasyMock.expect(carDao.findById(1L)).andReturn(Optional.of(car));
        EasyMock.expect(carDao.save(car)).andReturn(car);
        carShopService = new CarShopService(carDao,userDao);
        EasyMock.replay(carDao);
        Car result = carShopService.update(1L, car);
        EasyMock.verify(carDao);
        assertEquals(car, result);
    }

}
