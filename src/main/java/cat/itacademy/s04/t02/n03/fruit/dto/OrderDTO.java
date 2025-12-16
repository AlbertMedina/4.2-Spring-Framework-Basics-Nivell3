package cat.itacademy.s04.t02.n03.fruit.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {

    @NotBlank
    private String customerName;

    @NotNull
    @Future
    private LocalDate deliveryDate;

    @NotEmpty
    private List<OrderItemDTO> items;

    public OrderDTO() {
    }

    public OrderDTO(String customerName, LocalDate deliveryDate, List<OrderItemDTO> items) {
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
        this.items = items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<OrderItemDTO> getItems() {
        return List.copyOf(items);
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
