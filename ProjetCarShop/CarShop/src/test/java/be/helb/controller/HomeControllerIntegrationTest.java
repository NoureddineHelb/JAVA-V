package be.helb.controller;

import be.helb.DAO.CarDao;
import be.helb.model.Car;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerIntegrationTest {

    @Test
    public void whenRequestGet_ThenOK() {
        Response response = get("/api/cars");
        assertEquals(200, response.getStatusCode());
        List<Car> cars = response.getBody().as(List.class);
        assertTrue(cars.size() > 0);
    }

    @Test
    public void whenRequestPost_ThenOK() {
        Car car = new Car();
        car.setBrand("Audi");
        car.setModel("rs7");
        car.setYear(2014);
        car.setPrice(28001);
        RestAssured.with().contentType(ContentType.JSON).body(car).request("POST", "/api/cars/cars/12").then().statusCode(200);
    }

    @Test
    public void whenRequestPut_ThenOK() {
        Car car = new Car();
        car.setBrand("Audi");
        car.setModel("rs7");
        car.setYear(2014);
        car.setPrice(28528);
        RestAssured.with().contentType(ContentType.JSON).body(car).request("PUT", "/api/cars/cars/12").then().statusCode(200);
    }


    @Test
    public void testSearchCars() {
        RestAssured.with().when().request("GET", "/api/cars/search?brand=BMW&model=i30").then().statusCode(200);
    }
}
