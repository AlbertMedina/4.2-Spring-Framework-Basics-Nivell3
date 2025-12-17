package cat.itacademy.s04.t02.n03.fruit.controllers;

import cat.itacademy.s04.t02.n03.fruit.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.fruit.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.fruit.model.Order;
import cat.itacademy.s04.t02.n03.fruit.model.OrderItem;
import cat.itacademy.s04.t02.n03.fruit.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderService orderService;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public OrderService orderService() {
            return Mockito.mock(OrderService.class); // Mock manual
        }
    }

    @Test
    void createOrder_shouldReturnCreatedOrder() throws Exception {
        when(orderService.createOrder(any(OrderDTO.class))).thenReturn(new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3))));

        mockMvc.perform(post("/orders").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrderDTO("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItemDTO("Watermelon", 2), new OrderItemDTO("Melon", 3))))))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.customerName").value("Albert"));
    }

    @Test
    void updateOrder_shouldReturnUpdatedOrder() throws Exception {
        when(orderService.updateOrder(Mockito.eq("1"), any(OrderDTO.class))).thenReturn(new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3))));

        mockMvc.perform(put("/orders/{id}", "1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(new OrderDTO("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItemDTO("Watermelon", 2), new OrderItemDTO("Melon", 3))))))
                .andExpect(status().isOk()).andExpect(jsonPath("$.customerName").value("Albert"));
    }

    @Test
    void removeOrder_shouldRemoveOrderAndReturnNoContent() throws Exception {
        doNothing().when(orderService).removeOrder("1");

        mockMvc.perform(delete("/orders/{id}", "1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getOrderById_shouldReturnCorrectOrder() throws Exception {
        when(orderService.getOrderById("1")).thenReturn(new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3))));

        mockMvc.perform(get("/orders/{id}", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.customerName").value("Albert"));
    }

    @Test
    void getAllOrders_shouldReturnAllExistingOrders() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(new Order("Albert", LocalDate.of(2026, 1, 1), List.of(new OrderItem("Watermelon", 2), new OrderItem("Melon", 3))), new Order("Joao", LocalDate.of(2026, 2, 2), List.of(new OrderItem("Apple", 1)))));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].customerName").value("Albert")).andExpect(jsonPath("$[1].customerName").value("Joao"));
    }
}
