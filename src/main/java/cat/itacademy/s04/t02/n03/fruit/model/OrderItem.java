package cat.itacademy.s04.t02.n03.fruit.model;

public class OrderItem {

    private String fruitName;
    private int quantityInKg;

    public OrderItem() {
    }

    public OrderItem(String fruitName, int quantityInKg) {
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
