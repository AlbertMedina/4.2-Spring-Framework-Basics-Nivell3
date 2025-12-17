package cat.itacademy.s04.t02.n03.fruit.services;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderServicesImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrder_shouldSaveOrder() {
        OrderDTO orderDTO = new OrderDTO("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItemDTO("Watermelon", 2), new OrderItemDTO("Melon", 3)));

        when(orderRepository.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        Order order = orderService.createOrder(orderDTO);

        assertNotNull(order);
        assertEquals("Albert", order.getCustomerName());
        assertEquals(LocalDate.of(2026, 1, 1), order.getDeliveryDate());
        assertEquals(2, order.getItems().size());
        assertEquals("Watermelon", order.getItems().get(0).getFruitName());
        assertEquals(2, order.getItems().get(0).getQuantityInKg());
        assertEquals("Melon", order.getItems().get(1).getFruitName());
        assertEquals(3, order.getItems().get(1).getQuantityInKg());

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void updateOrder_shouldUpdateExistingOrder() {
        Order order = new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3)));

        when(orderRepository.findById("1")).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrderDTO orderDTO = new OrderDTO("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItemDTO("Watermelon", 2), new OrderItemDTO("Melon", 3)));

        Order updated = orderService.updateOrder("1", orderDTO);

        assertEquals("Albert", updated.getCustomerName());
        assertEquals(LocalDate.of(2026, 1, 1), updated.getDeliveryDate());
        assertEquals(2, updated.getItems().size());
        assertEquals("Watermelon", updated.getItems().get(0).getFruitName());
        assertEquals(2, updated.getItems().get(0).getQuantityInKg());
        assertEquals("Melon", updated.getItems().get(1).getFruitName());
        assertEquals(3, updated.getItems().get(1).getQuantityInKg());


        verify(orderRepository).findById("1");
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void removeOrder_shouldDeleteExistingOrder() {
        when(orderRepository.existsById("1")).thenReturn(true);
        doNothing().when(orderRepository).deleteById("1");

        assertDoesNotThrow(() -> orderService.removeOrder("1"));

        verify(orderRepository).existsById("1");
        verify(orderRepository).deleteById("1");
    }

    @Test
    void getOrderById_shouldReturnOrder() {
        Order order = new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3)));

        when(orderRepository.findById("1")).thenReturn(Optional.of(order));

        Order result = orderService.getOrderById("1");

        assertNotNull(result);
        assertEquals("Albert", result.getCustomerName());
        assertEquals(LocalDate.of(2026, 1, 1), result.getDeliveryDate());
        assertEquals(2, result.getItems().size());
        assertEquals("Watermelon", result.getItems().get(0).getFruitName());
        assertEquals(2, result.getItems().get(0).getQuantityInKg());
        assertEquals("Melon", result.getItems().get(1).getFruitName());
        assertEquals(3, result.getItems().get(1).getQuantityInKg());

        verify(orderRepository).findById("1");
    }

    @Test
    void getAllOrders_shouldReturnListOfOrders() {
        Order order1 = new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3)));
        Order order2 = new Order("Joao", LocalDate.of(2026, 2, 2), List.of(new OrderItem("Apple", 1)));

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        List<Order> orders = orderService.getAllOrders();

        assertEquals(2, orders.size());
        assertEquals("Albert", orders.get(0).getCustomerName());
        assertEquals("Joao", orders.get(1).getCustomerName());

        verify(orderRepository).findAll();
    }
}
