package business.mappers;

import business.entities.Carport;
import business.entities.Roof;
import business.entities.Shed;
import business.entities.User;
import business.entities.materials.Material;
import business.entities.materials.RoofFlat;
import business.entities.views.OrderView;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.MaterialFacade;
import business.services.UserFacade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderViewMapper {

    private Database database;

    public OrderViewMapper(Database database) {
        this.database = database;
    }

    public List<OrderView> getAllOrderViews(User user) {
        List<OrderView> allOrderViews = null;

        MaterialFacade materialFacade = new MaterialFacade(database);
        UserFacade userFacade = new UserFacade(database);

        try (Connection connection = database.connect()) {
            String sql = "";
            if (user == null)
                sql = "SELECT * FROM orders_carport_view";
            else
                sql = "SELECT * FROM orders_carport_view WHERE user_id=?";

            PreparedStatement ps = connection.prepareStatement( sql );
            if (user != null)
                ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (allOrderViews == null) {
                    allOrderViews = new ArrayList<>();
                }

                // Order
                int orderId = rs.getInt("order_id");
                String status = rs.getString("status");
                int userId = rs.getInt("user_id");
                String created = rs.getString("created");
                String deliveryDate = rs.getString("delivery_date");

                // Carport
                int carportId = rs.getInt("carport_id");
                int width = rs.getInt("width");
                int length = rs.getInt("length");
                int height = rs.getInt("height");

                // Roof
                int roofId = rs.getInt("roof_id");
                int roofTypeId = rs.getInt("roof_type_id");
                int slope = rs.getInt("slope");
                String roofName = rs.getString("roof_name");

                // Shed
                Shed shed = null;
                String placement = rs.getString("location");
                if (placement != null) {
                    int shedId = rs.getInt("shed_id");
                    int claddingId = rs.getInt("cladding_id");
                    int shedWidth = rs.getInt("shed_width");
                    int shedLength = rs.getInt("shed_length");

                    Material cladding = materialFacade.getSpecificMaterial(claddingId);

                    shed = new Shed(shedId, cladding, placement, shedWidth, shedLength);
                }

                // Roof
                Material roofMaterial = materialFacade.getSpecificMaterial(roofTypeId);
                Roof roof = new Roof(roofId, roofMaterial, slope);

                // Carport
                Carport carport = new Carport(carportId, width, length, height, shed, roof);

                // User
                User orderUser = null;
                try {orderUser = userFacade.getUserById(userId);}
                catch (UserException e) {e.printStackTrace();}

                // Overview
                OrderView orderView = new OrderView(orderId, status, orderUser, created, deliveryDate, carport, roof, shed);

                // Add to list
                allOrderViews.add(orderView);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return allOrderViews;
    }


    public OrderView getOrderViewByOrderId(int orderId, User user) {
        List<OrderView> allOrderViews = getAllOrderViews(user);
        for (OrderView ov : allOrderViews) {
            if (ov.getOrderId() == orderId) {
                return ov;
            }
        }
        return null;
    }
}
