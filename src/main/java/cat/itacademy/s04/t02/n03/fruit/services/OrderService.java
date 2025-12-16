package cat.itacademy.s04.t02.n03.fruit.services;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO);

    Order updateOrder(String id, OrderDTO orderDTO);

    void removeOrder(String id);

    Order getOrderById(String id);

    List<Order> getAllOrders();
}
