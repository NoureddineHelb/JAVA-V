package be.helb.controller;

import be.helb.DAO.UserDao;
import be.helb.model.Car;
import be.helb.model.User;
import be.helb.service.CarShopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class controller {
    @Autowired
    private CarShopService carShopService;
    @Autowired
    private UserDao userDao;

    public controller(CarShopService carShopService){
        this.carShopService = carShopService;
    }

    //afficher et lister des données en json
    @ApiOperation(value = "Opération pour afficher et lister des données en json")
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carShopService.getAllCar();
        return ResponseEntity.ok(cars);
    }

    //Poster de nouvelles données.
    @ApiOperation(value = "Opération pour poster de nouvelles données (faut introduire que : brand, model, price et year)")
    @PostMapping("/cars/")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        // Récupérez l'utilisateur actuellement connecté
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        // Assignez l'utilisateur au champ "user" de l'objet voiture
        car.setUser(user);
        // Sauvegardez la voiture en utilisant le service CarShopService
        Car savedCar = carShopService.saveNewCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    //Updater des données existante
    @ApiOperation(value = "Opération pour mettre à jour les données existantes (faut introduire que : brand, model, price et year) et choisir l'id du véhicule a update")
    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        try {
            Car updatedCar = carShopService.update(id, car);
            return ResponseEntity.ok(updatedCar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // fonction qui affiche des résultat en se basant sur des préférence de l’utilisateurs
    @ApiOperation(value = "Opération pour afficher des résultat en se basant sur la marque et le model")
    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam("brand") String brand, @RequestParam("model") String model) {
        List<Car> searchResults = carShopService.searchCars(brand, model);
        return ResponseEntity.ok(searchResults);
    }

}
