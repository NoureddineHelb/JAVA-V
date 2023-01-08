package be.helb.DAO;

import be.helb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Optional<User> findById(Long id);
}