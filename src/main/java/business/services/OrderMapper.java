package business.services;

import business.entities.Order;
import business.exceptions.OrderException;
import business.exceptions.UserException;
import business.persistence.Database;

import java.sql.*;

public class OrderMapper {
    private Database database;

    protected OrderMapper(Database database) {
        this.database = database;
    }

    protected Order createOrder(Order order) throws OrderException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO orders (status, user_id, delivery_date, price_reduction, price_increase) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, order.getStatus());
                ps.setInt(2, order.getUser().getId());
                ps.setString(3, "ukendt");
                ps.setInt(4,50);
                ps.setInt(5,70);
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                order.setOrderId(id);
                return order;

            } catch (SQLException ex) {
                throw new OrderException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }

    protected void updateOrderStatus(int orderID, String status) {

        try (Connection connection = database.connect()) {

            String sql = "UPDATE orders SET status=? WHERE order_id=?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, orderID);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
