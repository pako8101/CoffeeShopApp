package CoffeeShopApp.services;

import CoffeeShopApp.models.service.OrderServiceModel;
import CoffeeShopApp.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModel orderServiceModel);

    List<OrderViewModel> findAllOrdersOderByPriceDesc();

    void readyOrder(Long id);
}
