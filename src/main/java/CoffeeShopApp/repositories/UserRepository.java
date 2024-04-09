package CoffeeShopApp.repositories;

import CoffeeShopApp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User>findByUsernameAndPassword(String username,String password);
    @Query("select u from  User u order by size(u.orders) desc " )
    List<User>findAllByOrdersCountDesc();
}
