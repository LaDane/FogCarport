package business.carportCalc;

import business.entities.Carport;
import business.entities.OrderLine;
import business.entities.Roof;
import business.entities.Shed;
import business.persistence.Database;
import business.services.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FittingsCalculatorTest {
    private final static String DATABASE = "fog";  // Change this to your own database
    private final static String USER = "root";
    private final static String PASSWORD = getSecret();
    private final static String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?serverTimezone=CET&useSSL=false";

    private static Database database;
    private static MaterialFacade materialFacade;
    private static Carport carportNoShed;
    private static Carport carportWithShed;

    private static CarportCalculator carportCalculator;
    private static FittingsCalculator fittingsCalculator;

    private static List<OrderLine> carportOrderLines;
    private static List<OrderLine> orderLines;


    @BeforeAll
    public static void setUpClass() {
        try {
            database = new Database(USER, PASSWORD, URL);
            materialFacade = new MaterialFacade(database);
            carportCalculator = new CarportCalculator(materialFacade);
            fittingsCalculator = new FittingsCalculator(materialFacade);
        } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
            fail("Database connection failed. Missing jdbc driver");
        }

        // Setup test Carport

        Shed shed = new Shed(materialFacade.getSpecificMaterial(26), "", 480, 200);
        Roof roof = new Roof(materialFacade.getSpecificMaterial(21), 0);
        carportNoShed = new Carport(480, 600, null, roof);
        carportNoShed.setHeight(250);
        carportWithShed = new Carport(480, 600, shed, roof);
        carportWithShed.setHeight(250);

        carportOrderLines = carportCalculator.calculateCarportMaterials(carportWithShed);
        orderLines = fittingsCalculator.calculateCarportMaterials(carportOrderLines, carportWithShed);


    }

    @Test
    public void testCalculateCarport() {

        assertEquals(10, orderLines.size());
    }

    @Test
    public void testCalculateFittingsPrice(){
        carportOrderLines = carportCalculator.calculateCarportMaterials(carportWithShed);
        int price = (int) fittingsCalculator.calculateFittingsPrice(carportOrderLines, carportWithShed);
        assertEquals(5668, price);
    }


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
        } catch (FileNotFoundException ignored) {
        }
        return secretWord;
    }
}