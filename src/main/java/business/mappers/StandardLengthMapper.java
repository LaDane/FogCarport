package business.mappers;

import business.entities.User;
import business.exceptions.UserException;
import business.persistence.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StandardLengthMapper {
    private Database database;

    public StandardLengthMapper(Database database) {
        this.database = database;
    }

    public List<Integer> getStandardLengths() {
        List<Integer> standardLengths = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sql = "SELECT length FROM standard_lengths";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int length = rs.getInt("length");
                    standardLengths.add(length);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return standardLengths;
    }

    public List<Integer> getCarportLengths(){
        List<Integer> standardLengths = getStandardLengths();
        List<Integer> carportLengths = new ArrayList<>();

        for (Integer standardLength : standardLengths) {
            if(standardLength>240){
                carportLengths.add(standardLength);
            }
        }
        return carportLengths;
    }

//    public List<Integer> getShedLengths(){
//        List<Integer> standardLengths = getStandardLengths();
//        List<Integer> shedLengths = new ArrayList<>();
//
//        for (Integer standardLength : standardLengths) {
//            if(standardLength>240){
//                shedLengths.add(standardLength);
//            }
//        }
//        return shedLengths;
//    }

}
