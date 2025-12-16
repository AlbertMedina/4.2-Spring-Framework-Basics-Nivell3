package cat.itacademy.s04.t02.n03.fruit.services;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getCustomerName(), orderDTO.getDeliveryDate(), mapToOrderItems(orderDTO.getItems()));
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(String id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order " + id + " not found"));

        order.setCustomerName(orderDTO.getCustomerName());
        order.setDeliveryDate(orderDTO.getDeliveryDate());
        order.setItems(mapToOrderItems(orderDTO.getItems()));

        return orderRepository.save(order);
    }

    @Override
    public void removeOrder(String id) {
        if (!orderRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order " + id + " not found");
        }

        orderRepository.deleteById(id);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order " + id + " not found"));
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    private List<OrderItem> mapToOrderItems(List<OrderItemDTO> itemDTOs) {
        return itemDTOs.stream().map(dto -> new OrderItem(dto.getFruitName(), dto.getQuantityInKg())).toList();
    }
}
