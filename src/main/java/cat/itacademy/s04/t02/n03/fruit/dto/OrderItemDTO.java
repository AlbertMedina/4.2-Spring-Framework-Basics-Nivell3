package cat.itacademy.s04.t02.n03.fruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OrderItemDTO {

    @NotBlank
    private String fruitName;

    @Positive
    private int quantityInKg;

    public OrderItemDTO() {
    }

    public OrderItemDTO(String fruitName, int quantityInKg) {
        this.fruitName = fruitName;
        this.quantityInKg = quantityInKg;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getQuantityInKg() {
        return quantityInKg;
    }

    public void setQuantityInKg(int quantityInKg) {
        this.quantityInKg = quantityInKg;
    }
}
