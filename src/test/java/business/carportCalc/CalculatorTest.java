package business.carportCalc;

import business.entities.Carport;
import business.entities.Roof;
import business.entities.Shed;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();
    Shed shed = new Shed(null, "NW", 600, 210);
    Carport carport = new Carport(600, 780, shed, new Roof(null, 0));

    @Test
    public void testRoofFlatRafters() {

        double lysVidde = calculator.getRafterLysViddeTrapez(carport);

        assertEquals(521, lysVidde);

        int amountOfRafters = calculator.getAmountOfRaftersTrapez(carport);

        assertEquals(18, amountOfRafters);

    }

    @Test
    public void testAmountOfRafters() {


        double lysVidde = calculator.getRafterLysViddeTrapez(carport);
        double rafterHeight = calculator.getRafterHeightFromLysVidde(carport);
        if (rafterHeight == -1)
            System.out.println("The carport is too wide, FOG can not supply rafters that can support this construction");

        double rafterDistance = calculator.getDistanceRafters(carport);
        int raftersAmount = calculator.getAmountOfRaftersTrapez(carport);

        System.out.println("lys vidde "+ lysVidde);
        System.out.println("rafter height: "+ rafterHeight);
        System.out.println("rafter amount: "+ raftersAmount);
        System.out.println("rafter distance: "+ rafterDistance);
        assertEquals(18, raftersAmount);
    }

    @Test
    public void testTrapezLength() {
        int amountOfTrapez = calculator.getAmountTrapezRow(carport);
        int lengthOfTrapez = calculator.getLengthOfTrapez(carport);
        assertEquals(2, amountOfTrapez);
        assertEquals(420, lengthOfTrapez);
    }

    @Test
    public void testTrapezTotal() {
        int totalAmout = calculator.getTotalAmountOfTrapez(carport);
        assertEquals(12, totalAmout);

    }

    @Test
    public void testAmountOfPosts() {
        int postAmount = calculator.getAmountOfPosts(carport);
        assertEquals(10, postAmount);
    }

    @Test
    public void testAmountAndLengthOfUnderStern() {
        int amountOfUnderStern = calculator.getAmountOfStern(carport);
        int lengthOfUnderStern = calculator.getLengthOfStern(carport);

        assertEquals(6, amountOfUnderStern);
        assertEquals(600, lengthOfUnderStern);
    }

    @Test
    public void testMetersShedCladdingKlink() {
        carport.setHeight(250);
        int totalSquareMeters = (int) calculator.getSquareMeterShedCladdingKlink(carport);
        int totalMeters = (int) calculator.getTotalMetersShedCladdingKlink(carport);
//        System.out.println("Square meters: "+ totalSquareMeters);
//        System.out.println("Meters: "+ totalMeters);
        assertEquals(52, totalSquareMeters);
        assertEquals(478, totalMeters);
    }

    @Test
    public void testGetCladding() {
        carport.setHeight(250);
        int[] claddingArray = calculator.getCladdingKlink(carport);
        int claddingLength = claddingArray[0];
        int claddingAmount = claddingArray[1];
        System.out.println("Cladding length: "+ claddingLength);
        System.out.println("Cladding Amount: "+ claddingAmount);
        System.out.println("Cladding Cost: "+ claddingAmount * 202);
        assertEquals(2, claddingArray.length);

    }

    @Test
    public void testRafterAmount() {
        int rafterAmount = calculator.getAmountOfRaftersTrapez(carport);
        System.out.println(rafterAmount);
    }

    @Test
    public void testAmountOfPostsWithShed() {
        // Test 3 different sizes
        Shed shed = new Shed(null, "N", 0, 0);

        Carport carport1 = new Carport(270, 270, shed, new Roof(null, 0));
        Carport carport2 = new Carport(420, 420, shed, new Roof(null, 0));
        Carport carport3 = new Carport(600, 780, shed, new Roof(null, 0));

        int postAmount0 = calculator.getAmountOfPosts(carport1);
        assertEquals(8, postAmount0);

        int postAmount1 = calculator.getAmountOfPosts(carport2);
        assertEquals(8, postAmount1);

        int postAmount2 = calculator.getAmountOfPosts(carport3);
        assertEquals(10, postAmount2);
    }
}