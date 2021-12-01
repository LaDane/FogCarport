package business.entities;

public class OrderLine {
    private int orderLineId;
    private Material material;
    private int length;
    private int amount;
    private double price;

    public OrderLine(int orderLineId, Material material, int length, int amount, double price) {
        this.orderLineId = orderLineId;
        this.material = material;
        this.length = length;
        this.amount = amount;
        this.price = price;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public void setOrderLineId(int orderLineId) {
        this.orderLineId = orderLineId;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
