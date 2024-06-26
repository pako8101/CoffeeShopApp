package CoffeeShopApp.repositories;

import CoffeeShopApp.models.entities.Category;
import CoffeeShopApp.models.enums.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category>findByName(CategoryNameEnum name);
}
