package business.services;

import business.entities.Carport;
import business.entities.Order;
import business.entities.Shed;
import business.exceptions.OrderException;
import business.mappers.CarportMapper;
import business.mappers.OrderMapper;
import business.mappers.RoofMapper;
import business.mappers.ShedMapper;
import business.persistence.Database;

public class OrderFacade {

    OrderMapper orderMapper;
    CarportMapper carportMapper;
    ShedMapper shedMapper;
    RoofMapper roofMapper;


    public OrderFacade(Database database) {
        this.orderMapper = new OrderMapper(database);
        this.carportMapper = new CarportMapper(database);
        this.shedMapper = new ShedMapper(database);
        this.roofMapper = new RoofMapper(database);
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

    public void createOrderEntry(Order sentOrder) throws OrderException {
        Order orderEntry = createOrder(sentOrder);
        Carport carportEntry = createCarport(sentOrder.getCarport(), orderEntry);
        createRoof(carportEntry);

        if (sentOrder.getCarport().getShed() != null) {
            Shed shedEntry = createShed(carportEntry.getShed(), carportEntry);
        }
    }

}