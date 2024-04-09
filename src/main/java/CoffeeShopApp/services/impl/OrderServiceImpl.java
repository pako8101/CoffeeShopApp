package CoffeeShopApp.services.impl;

import CoffeeShopApp.models.entities.Order;
import CoffeeShopApp.models.service.OrderServiceModel;
import CoffeeShopApp.repositories.OrderRepository;
import CoffeeShopApp.services.CategoryService;
import CoffeeShopApp.services.OrderService;
import CoffeeShopApp.services.UserService;
import CoffeeShopApp.util.CurrentUser;
import CoffeeShopApp.view.OrderViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderServiceImpl(ModelMapper modelMapper, OrderRepository orderRepository, CurrentUser currentUser, UserService userService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.orderRepository = orderRepository;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        Order order = modelMapper.map(orderServiceModel,Order.class);
order.setEmployee(userService.findById(currentUser.getId()));
order.setCategory(categoryService.findByCategoryNameEnum(
        orderServiceModel.getCategory()
));

orderRepository.saveAndFlush(order);
    }

    @Override
    public List<OrderViewModel> findAllOrdersOderByPriceDesc() {
        return this.orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(order -> modelMapper
                        .map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void readyOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
