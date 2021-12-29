package business.services;

import business.exceptions.UserException;
import business.entities.User;
import business.persistence.Database;

import java.sql.*;

public class UserMapper {
    private Database database;

    protected UserMapper(Database database) {
        this.database = database;
    }

    protected void createUser(User user) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "INSERT INTO users (email, password, role, name, address, zip, city, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getRole());
                ps.setString(4, user.getName());
                ps.setString(5, user.getAddress());
                ps.setInt(6, user.getZip());
                ps.setString(7, user.getCity());
                ps.setString(8, user.getPhoneNumber());
                ps.executeUpdate();
                ResultSet ids = ps.getGeneratedKeys();
                ids.next();
                int id = ids.getInt(1);
                user.setId(id);
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException(ex.getMessage());
        }
    }

    protected User login(String email, String password) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT user_id, role, name, address, zip, city, phone_number FROM users WHERE email=? AND password=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String role = rs.getString("role");
                    int id = rs.getInt("user_id");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int zip = rs.getInt("zip");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phone_number");
                    User user = new User(email, password, role, name, address, zip, city, phoneNumber);
                    user.setId(id);
                    return user;
                } else {
                    throw new UserException("Could not validate user");
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }


    protected boolean emailExist(String email)  throws UserException{
        try (Connection connection = database.connect()) {
            String sql = "SELECT user_id FROM users WHERE email=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {

                    return true;
                }
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
        return false;
    }


    // So far its only used for testing purpose.
    protected void deleteUser(int user_id) throws UserException {

        try (Connection connection = database.connect()) {
            String sql = "DELETE FROM users WHERE user_id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, user_id);
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }

    protected User getUserById(int userId) throws UserException {
        try (Connection connection = database.connect()) {
            String sql = "SELECT * FROM users WHERE user_id=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    String email = rs.getString("email");
                    String role = rs.getString("role");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    int zip = rs.getInt("zip");
                    String city = rs.getString("city");
                    String phoneNumber = rs.getString("phone_number");
                    return new User(userId, email, role, name, address, zip, city, phoneNumber);
                }
                else {
                    throw new UserException("Could not validate user");
                }

            } catch (SQLException ex) {
                throw new UserException(ex.getMessage());
            }

        } catch (SQLException ex) {
            throw new UserException("Connection to database could not be established");
        }
    }
}
