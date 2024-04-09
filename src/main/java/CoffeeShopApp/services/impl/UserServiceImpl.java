package CoffeeShopApp.services.impl;

import CoffeeShopApp.models.entities.User;
import CoffeeShopApp.models.service.UserServiceModel;
import CoffeeShopApp.repositories.UserRepository;
import CoffeeShopApp.services.UserService;
import CoffeeShopApp.view.UserViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CoffeeShopApp.util.CurrentUser;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
private final UserRepository userRepository;
private final ModelMapper modelMapper;
private final CurrentUser currentUser;
@Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
                           CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper
                .map(userServiceModel, User.class);

        return modelMapper.map(userRepository.save(user),UserServiceModel.class);



    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password)
                .map(user -> modelMapper.map(user,UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);


    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<UserViewModel> findAllUsersAndCountOfOrdersOrderByCountDesc() {
        return null;
    }
}
