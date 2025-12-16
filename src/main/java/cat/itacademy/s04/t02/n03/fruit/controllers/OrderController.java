package cat.itacademy.s04.t02.n03.fruit.controllers;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody @Valid OrderDTO orderDTORequest) {
        Order order = orderService.createOrder(orderDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody @Valid OrderDTO orderDTORequest) {
        Order order = orderService.updateOrder(id, orderDTORequest);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> removeOrder(@PathVariable String id) {
        orderService.removeOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
