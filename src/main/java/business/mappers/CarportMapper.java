package business.mappers;

import business.entities.Carport;
import business.entities.Order;
import business.exceptions.OrderException;
import business.persistence.Database;

import java.sql.*;

public class CarportMapper {

    private Database database;

    public CarportMapper(Database database)
    {
        this.database = database;
    }

    public Carport createCarport(Carport carport, Order order) throws OrderException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO carports (order_id, width, length, height) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, order.getOrderId());
                ps.setInt(2, carport.getWidth());
                ps.setInt(3, carport.getLength());
                ps.setInt(4, carport.getHeight());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                carport.setCarportId(id);
                return carport;

            } catch (SQLException ex) {
                throw new OrderException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }
}
