package business.carportCalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testRoofFlatRafters() {
        // Params
        double length = 600;
        double width = 420;

        double lysVidde = calculator.getRafterLysViddeTrapez(width);

        // Wood dimensions
        double raftWidth = 4.5;
        double raftHeight = calculator.getRafterHeightFromLysVidde(lysVidde);

        assertEquals(341, lysVidde);

        int amountOfRafters = calculator.getAmountOfRaftersTrapez(length, lysVidde);
//        double distanceBetweenRafters = CarportCalculator.getDistanceBetweenRaftersTrapez(length, amountOfRafters);

        assertEquals(3, amountOfRafters);
//        assertEquals(66.67, distanceBetweenRafters);
    }

    @Test
    public void testAmountOfRafters() {
        double length = 500;
        double width = 480;

        double lysVidde = calculator.getRafterLysViddeTrapez(width);
        double rafterHeight = calculator.getRafterHeightFromLysVidde(lysVidde);
        if (rafterHeight == -1)
            System.out.println("The carport is too wide, FOG can not supply rafters that can support this construction");

        double rafterDistance = calculator.getDistanceRafters(lysVidde, rafterHeight, length);
        int raftersAmount = calculator.getAmountOfRaftersTrapez(length, rafterDistance);

        System.out.println("lys vidde "+ lysVidde);
        System.out.println("rafter height: "+ rafterHeight);
        System.out.println("rafter amount: "+ raftersAmount);
        System.out.println("rafter distance: "+ rafterDistance);
        assertEquals(11, raftersAmount);
    }

    @Test
    public void testTrapezLength() {
        double length = 580;
        int amountOfTrapez = calculator.getAmountTrapezRow(length);
        int lengthOfTrapez = calculator.getLengthOfTrapez(length);
        assertEquals(2, amountOfTrapez);
        assertEquals(360, lengthOfTrapez);
    }

    @Test
    public void testTrapezTotal() {
        calculator.getTotalAmountOfTrapez(580, 550);
    }


}