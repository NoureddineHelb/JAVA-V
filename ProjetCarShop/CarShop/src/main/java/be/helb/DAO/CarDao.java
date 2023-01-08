package be.helb.DAO;

import be.helb.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao extends JpaRepository<Car, Long>{

    // recup all my cars
    List<Car> findAll();
    // fonction qui affiche des résultat en se basant sur des préférence de l’utilisateurs
    List<Car> findByBrandAndModel(String brand, String model);
}
