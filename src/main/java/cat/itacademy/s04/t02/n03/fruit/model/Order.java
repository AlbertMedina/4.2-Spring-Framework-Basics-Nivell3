package cat.itacademy.s04.t02.n03.fruit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String customerName;
    private LocalDate deliveryDate;
    private List<OrderItem> items;

    public Order() {
    }

    public Order(String customerName, LocalDate deliveryDate, List<OrderItem> items) {
        this.customerName = customerName;
        this.deliveryDate = deliveryDate;
        this.items = items;
    }

    public String getId() {
        return id;
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

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
