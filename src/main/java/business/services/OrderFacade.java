package business.services;

import business.entities.Carport;
import business.entities.Order;
import business.entities.Shed;
import business.entities.User;
import business.entities.views.OrderView;
import business.exceptions.OrderException;
import business.mappers.*;
import business.persistence.Database;

import java.util.List;

public class OrderFacade {

    OrderMapper orderMapper;
    CarportMapper carportMapper;
    ShedMapper shedMapper;
    RoofMapper roofMapper;
    OrderViewMapper orderViewMapper;


    public OrderFacade(Database database) {
        this.orderMapper = new OrderMapper(database);
        this.carportMapper = new CarportMapper(database);
        this.shedMapper = new ShedMapper(database);
        this.roofMapper = new RoofMapper(database);
        this.orderViewMapper = new OrderViewMapper(database);
    }

    public Order createOrder(Order order) throws OrderException {
        return orderMapper.createOrder(order);
    }

    public Carport createCarport(Carport carport, Order order) throws OrderException {
        return carportMapper.createCarport(carport, order);
    }

    public Shed createShed(Shed shed, Carport carport) throws OrderException {
        return shedMapper.createShed(shed, carport);
    }

    public void createRoof(Carport carport) throws OrderException {
        roofMapper.createRoof(carport);
    }

    public int createOrderEntry(Order sentOrder) throws OrderException {
        Order orderEntry = createOrder(sentOrder);
        Carport carportEntry = createCarport(sentOrder.getCarport(), orderEntry);
        createRoof(carportEntry);

        if (sentOrder.getCarport().getShed() != null) {
            Shed shedEntry = createShed(carportEntry.getShed(), carportEntry);
        }
        return orderEntry.getOrderId();
    }

    public List<OrderView> getAllOrderViews(User user) {
        return orderViewMapper.getAllOrderViews(user);
    }

    public OrderView getOrderViewByOrderId(int orderId, User user) {
        return orderViewMapper.getOrderViewByOrderId(orderId, user);
    }

    public void updatePricePercent(int priceReduction, int priceIncrease, int orderID) {
        orderViewMapper.updatePricePercent(priceReduction,priceIncrease,orderID);
    }

    public void updateOrderStatus(int orderID, String status) {
        orderMapper.updateOrderStatus(orderID,status);
    }
}
