package business.persistence;

import business.entities.User;
import business.exceptions.UserException;
import business.mappers.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {
//    // OBS: Only works if you havee the fog_test Database in MySql.
//    private final static String DATABASE = "fog";  // Change this to your own database
//    private final static String TESTDATABASE = DATABASE + "_test";
//    private final static String USER = "dev";
//    private final static String PASSWORD = getSecret();
//    private final static String URL = "jdbc:mysql://localhost:3306/" + TESTDATABASE + "?serverTimezone=CET&useSSL=false";
//
//    private static Database database;
//    private static UserMapper userMapper;
//
//    @BeforeAll
//    public static void setUpClass() {
//        try {
//            database = new Database(USER, PASSWORD, URL);
//            userMapper = new UserMapper(database);
//        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
//            fail("Database connection failed. Missing jdbc driver");
//        }
//    }
//
//    @Test
//    public void testSetUpOK() {
//        // Just check that we have a connection.
//        assertNotNull(database);
//    }
//
//    @Test
//    public void testLogin01() throws UserException {
//        // Can we log in
//        User user = userMapper.login("test@test.dk", "1234");
//        assertTrue(user != null);
//    }
//
//    @Test
//    public void testLogin02() throws UserException {
//        // We should get an exception if we use the wrong password
//        assertThrows(UserException.class, () ->
//        {
//            User user = userMapper.login("test@test.dk", "4321");
//        });
//
//    }
//
//    @Test
//    public void testLogin03() throws UserException {
//        // Jens is supposed to be a customer
//        User user = userMapper.login("test@test.dk", "1234");
//        assertEquals("customer", user.getRole());
//    }
//
//    @Test
//    public void testCreateUser01() throws UserException {
//        // Can we create a new user - Notice, if login fails, this will fail
//        // but so would login01, so this is OK
//        User original = new User( "king@kong.com", "uhahvorhemmeligt", "konge" );
//        userMapper.createUser( original );
//        User retrieved = userMapper.login( "king@kong.com", "uhahvorhemmeligt" );
//        assertEquals( "konge", retrieved.getRole() );
//
//
//        // Makes sure to delete it again for further unittests, cause you can't have duplicate emails.
//        // Can't just drop table, because there is a foreign key restraints on the 'users' table.
//        try {
//            userMapper.deleteUser(original.getId());
//        } catch (UserException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testDeleteUser01() throws UserException {
//        // Delete the newly created user
//        userMapper.deleteUser(2);
//        User retrieved = userMapper.login( "king@kong.com", "uhahvorhemmeligt" );
//        assertEquals( null, retrieved.getRole() );
//    }
//
//
//    // Must have a file names password.secret located in the C drive to be able to connect to a SQL database
//    private static String getSecret() {
//        String secretWord = "file not found";
//
//        try {
//            Scanner scanner = new Scanner(new File("C:\\password.secret"));
//            secretWord = scanner.nextLine();
//            scanner.close();
//        } catch (FileNotFoundException ignored) {
//        }
//        return secretWord;
//    }

}
