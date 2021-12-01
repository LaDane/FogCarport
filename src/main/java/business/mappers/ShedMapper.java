package business.mappers;

import business.entities.Carport;
import business.entities.Shed;
import business.exceptions.OrderException;
import business.persistence.Database;

import java.sql.*;

public class ShedMapper {

    private Database database;

    public ShedMapper(Database database) {
        this.database = database;
    }

    public Shed createShed(Shed shed, Carport carport) throws OrderException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO sheds (carport_id, cladding_id, location, width, length) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, carport.getCarportId());
                ps.setInt(2, shed.getCladding().getMaterialId());
                ps.setString(3, shed.getPlacement());
                ps.setInt(4, shed.getWidth());
                ps.setInt(5, shed.getLength());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                shed.setShedId(id);
                return shed;

            } catch (SQLException ex) {
                throw new OrderException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new OrderException(ex.getMessage());
        }
    }
}
