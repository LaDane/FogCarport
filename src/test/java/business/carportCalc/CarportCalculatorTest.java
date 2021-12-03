package business.carportCalc;

import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class CarportCalculatorTest {

    @Test
    public void testRoofFlatRafters() {
        // Params
        double length = 600;
        double width = 420;

        double lysVidde = CarportCalculator.getRafterLysViddeTrapez(width);

        // Wood dimensions
        double raftWidth = 4.5;
        double raftHeight = CarportCalculator.getRafterHeightFromLysVidde(lysVidde);

        assertEquals(341, lysVidde);

        int amountOfRafters = CarportCalculator.getAmountOfRaftersTrapez(length, lysVidde);
//        double distanceBetweenRafters = CarportCalculator.getDistanceBetweenRaftersTrapez(length, amountOfRafters);

        assertEquals(10, amountOfRafters);
//        assertEquals(66.67, distanceBetweenRafters);
    }

    @Test
    public void testAmountOfRafters() {
        double length = 500;
        double width = 480;

        double lysVidde = CarportCalculator.getRafterLysViddeTrapez(width);
        double rafterHeight = CarportCalculator.getRafterHeightFromLysVidde(lysVidde);
        if (rafterHeight == -1)
            System.out.println("The carport is too wide, FOG can not supply rafters that can support this construction");

        double rafterDistance = CarportCalculator.getDistanceRafters(lysVidde, rafterHeight, length);
        int raftersAmount = CarportCalculator.getAmountOfRaftersTrapez(length, rafterDistance);

        System.out.println("lys vidde "+ lysVidde);
        System.out.println("rafter height: "+ rafterHeight);
        System.out.println("rafter amount: "+ raftersAmount);
        System.out.println("rafter distance: "+ rafterDistance);
        assertEquals(11, raftersAmount);
    }

    @Test
    public void testTrapezLength() {
        double length = 580;
        int amountOfTrapez = CarportCalculator.getAmountTrapezRow(length);
        int lengthOfTrapez = CarportCalculator.getLengthOfTrapez(length);
        assertEquals(2, amountOfTrapez);
        assertEquals(360, lengthOfTrapez);
    }

    @Test
    public void testTrapezTotal() {
        CarportCalculator.getTotalAmountOfTrapez(580, 550);
    }
}