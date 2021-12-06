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
import java.util.List;
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
   private static Carport carportNoShed;
   private static Carport carportWithShed;

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

      Shed shed = new Shed(materialFacade.getSpecificMaterial(26), "", 480, 200 );
      Roof roof = new Roof(materialFacade.getSpecificMaterial(21), 0);
      carportNoShed = new Carport(480, 600, null, roof);
      carportWithShed = new Carport(480, 600, shed, roof);

   }



   @Test
   public void testCalculateCarport(){
      List<OrderLine> orderLines = carportCalculator.calculateCarport(carportNoShed);

      for (OrderLine ol : orderLines) {
         //System.out.println(ol.getMaterial().getName() + " " + ol.getLength() + " " + ol.getAmount() + " " + ol.getPrice());
         System.out.println(ol);
      }

      assertEquals(2, orderLines.size());
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