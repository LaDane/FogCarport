package business.carportCalc;

import business.entities.Carport;
import business.entities.Roof;
import business.mappers.CarportMapper;
import business.mappers.MaterialMapper;
import business.persistence.Database;
import business.services.MaterialFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {
   private final static String DATABASE = "fog";  // Change this to your own database
   //private final static String TESTDATABASE = DATABASE + "_test";
   private final static String USER = "root";
   private final static String PASSWORD = getSecret();
   private final static String URL = "jdbc:mysql://localhost:3306/" + DATABASE + "?serverTimezone=CET&useSSL=false";

   private static Database database;
   private static MaterialFacade materialFacade;
   private static Carport carport;

   private static CarportCalculator carportCalculator;

   @BeforeAll
   public static void setUpClass() {
      try {
         database = new Database(USER, PASSWORD, URL);
         materialFacade = new MaterialFacade(database);
         carportCalculator = new CarportCalculator(materialFacade);
      } catch (ClassNotFoundException e) {   // kan ikke finde driveren i database klassen
         fail("Database connection failed. Missing jdbc driver");
      }

      // Setup test Carport

      Roof roof = new Roof(materialFacade.getSpecificMaterial(21), 0);
      carport = new Carport(480, 600, null, roof);
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
      } catch (FileNotFoundException ignored) {}
      return secretWord;
   }


   }