package business.persistence;

import business.entities.User;
import business.exceptions.UserException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class CarportMapperTest {

    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String TESTDATABASE = DATABASE + "_test";
    private final static String USER = "root";
    private final static String PASSWORD = getSecret();
    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static CarportMapper carportMapper;

    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            carportMapper = new CarportMapper(database);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }
    }

//    @BeforeEach
//    public void setUp() {
//
//        try {
//            userMapper.deleteUser(2);
//        } catch (UserException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testSetUpOK() {
        // Just check that we have a connection.
        assertNotNull(database);
    }






    // Must have a file names password.secret located in the C drive to be able to connect to a SQL database
    private static String getSecret() {
        String secretWord = "file not found";

        try {
            Scanner scanner = new Scanner(new File("C:\\password.secret"));
            secretWord = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException ignored) {}
        return secretWord;
    }

}
