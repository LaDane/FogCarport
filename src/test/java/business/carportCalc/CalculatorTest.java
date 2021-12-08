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
        // Params
        double length = 600;
        double width = 420;

        double lysVidde = calculator.getRafterLysViddeTrapez(carport);

        // Wood dimensions
        double raftWidth = 4.5;
        double raftHeight = calculator.getRafterHeightFromLysVidde(carport);

        assertEquals(341, lysVidde);

        int amountOfRafters = calculator.getAmountOfRaftersTrapez(carport);
//        double distanceBetweenRafters = CarportCalculator.getDistanceBetweenRaftersTrapez(length, amountOfRafters);

        assertEquals(3, amountOfRafters);
//        assertEquals(66.67, distanceBetweenRafters);
    }

    @Test
    public void testAmountOfRafters() {
        double length = 500;
        double width = 480;

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
        assertEquals(11, raftersAmount);
    }

    @Test
    public void testTrapezLength() {
        double length = 580;
        int amountOfTrapez = calculator.getAmountTrapezRow(carport);
        int lengthOfTrapez = calculator.getLengthOfTrapez(carport);
        assertEquals(2, amountOfTrapez);
        assertEquals(360, lengthOfTrapez);
    }

    @Test
    public void testTrapezTotal() {
        calculator.getTotalAmountOfTrapez(carport);
    }

    @Test
    public void testAmountOfPosts() {
        int postAmount = calculator.getAmountOfPosts(carport);
        assertEquals(8, postAmount);
    }

    @Test
    public void testAmountAndLengthOfUnderStern() {
        int amountOfUnderStern = calculator.getAmountOfStern(carport);
        int lengthOfUnderStern = calculator.getLengthOfStern(carport);

        assertEquals(4, amountOfUnderStern);
        assertEquals(600, lengthOfUnderStern);
    }

    @Test
    public void testMetersShedCladdingKlink() {
        carport.setHeight(250);
        double totalSquareMeters = calculator.getSquareMeterShedCladdingKlink(carport);
        double totalMeters = calculator.getTotalMetersShedCladdingKlink(carport);
//        System.out.println("Square meters: "+ totalSquareMeters);
//        System.out.println("Meters: "+ totalMeters);
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
    }

    @Test
    public void testRafterAmount() {
        int rafterAmount = calculator.getAmountOfRaftersTrapez(carport);
        System.out.println(rafterAmount);
    }
}