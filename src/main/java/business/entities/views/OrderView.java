package business.entities.views;

import business.entities.Carport;
import business.entities.Roof;
import business.entities.Shed;
import business.entities.User;
import business.entities.materials.Cladding;
import business.entities.materials.Material;

public class OrderView {
    int orderId;
    String status;
    User user;          // sql statement
    String created;
    String deliveryDate;
    Carport carport;
    Roof roof;
    Shed shed;

    public OrderView(int orderId, String status, User user, String created, String deliveryDate, Carport carport, Roof roof, Shed shed) {
        this.orderId = orderId;
        this.status = status;
        this.user = user;
        this.created = created;
        this.deliveryDate = deliveryDate;
        this.carport = carport;
        this.roof = roof;
        this.shed = shed;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Carport getCarport() {
        return carport;
    }

    public void setCarport(Carport carport) {
        this.carport = carport;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public Shed getShed() {
        return shed;
    }

    public void setShed(Shed shed) {
        this.shed = shed;
    }
}
