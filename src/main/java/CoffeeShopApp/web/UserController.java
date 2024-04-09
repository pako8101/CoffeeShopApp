package CoffeeShopApp.web;

import CoffeeShopApp.models.binding.UserLoginBindingModel;
import CoffeeShopApp.models.binding.UserRegisterBindingModel;
import CoffeeShopApp.models.entities.User;
import CoffeeShopApp.models.service.UserServiceModel;
import CoffeeShopApp.repositories.UserRepository;
import CoffeeShopApp.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")

public class UserController {
private final UserService userService;
private  final UserRepository userRepository;
private final ModelMapper modelMapper;

    public UserController(UserService userService, UserRepository userRepository, ModelMapper modelMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public  String register(){
        return "register";
    }
@PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()|| !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",
                    userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework" +
                            ".validation.BindingResult" +
                            ".userRegisterBindingModel",
                    bindingResult);


            return "redirect:register";
        }
        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

    return "redirect:login";
}
@GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("isFound")){
            model.addAttribute("isFound",true);
        }

        return "login";
}
@PostMapping("/login")
public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework" +
                            ".validation.BindingResult" +
                            ".userLoginBindingModel",
                    bindingResult);
            return "redirect:login";
        }
   UserServiceModel userServiceModel =     userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword());

    if (userServiceModel ==null){
        redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel);
     redirectAttributes.addFlashAttribute("isFound",false);
        return "redirect:login";
    }
        userService.loginUser(userServiceModel.getId(),userLoginBindingModel.getUsername());
        return "redirect:/";
}
@GetMapping("/logout")
public String logout(HttpSession httpSession){
httpSession.invalidate();
        return "redirect:/";
}


@ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
}
@ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
}
}
