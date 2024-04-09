package CoffeeShopApp.web;

import CoffeeShopApp.services.OrderService;
import CoffeeShopApp.services.UserService;
import CoffeeShopApp.util.CurrentUser;
import CoffeeShopApp.view.OrderViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class HomeController {
private final CurrentUser currentUser;
private  final OrderService orderService;
private final UserService userService;
    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        if (currentUser.getId()==null){
            return "index";
        }
      List<OrderViewModel> orders = orderService.findAllOrdersOderByPriceDesc();
model.addAttribute("orders", orders);
model.addAttribute("totalTime",orders
        .stream()
        .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                        .reduce(Integer::sum)
                .orElse(0)

                );
model.addAttribute("users",userService.findAllUsersAndCountOfOrdersOrderByCountDesc());

        return "home";
    }
}
