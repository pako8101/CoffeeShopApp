package CoffeeShopApp.services;

import CoffeeShopApp.models.entities.User;
import CoffeeShopApp.models.service.UserServiceModel;
import CoffeeShopApp.view.UserViewModel;

import java.util.List;

public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    User findById(Long id);

    List<UserViewModel> findAllUsersAndCountOfOrdersOrderByCountDesc();
}
