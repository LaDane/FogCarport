package business.services;

import business.entities.Carport;
import business.exceptions.OrderException;
import business.persistence.Database;

import java.sql.*;

public class RoofMapper {

    private Database database;

    protected RoofMapper(Database database) {
        this.database = database;
    }

    protected void createRoof(Carport carport) throws OrderException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO roofs (carport_id, roof_type_id, slope) VALUES (?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, carport.getCarportId());
                ps.setInt(2, carport.getRoof().getRoofMaterial().getMaterialId());
                ps.setInt(3, carport.getRoof().getSlope());
                ps.executeUpdate();

            } catch (SQLException ex) {
                throw new OrderException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }
}
