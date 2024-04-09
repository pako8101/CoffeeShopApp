package CoffeeShopApp.services;

import CoffeeShopApp.models.entities.Category;
import CoffeeShopApp.models.enums.CategoryNameEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryNameEnum(CategoryNameEnum categoryNameEnum);
}
