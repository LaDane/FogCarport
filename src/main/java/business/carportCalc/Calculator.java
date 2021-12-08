package business.carportCalc;

import business.entities.Carport;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Calculator {

    // Carport Spær

    public double getRafterLysViddeTrapez(Carport carport) {
        double width = (double) carport.getWidth();
        double distanceFromRemToEdge = 39.5;
        return width - (distanceFromRemToEdge * 2);
    }

    public double getRafterHeightFromLysVidde(Carport carport) {
        double lysVidde = getRafterLysViddeTrapez(carport);

        double raftHeight = 19.5;

        double maxLysVidde195 = 409.5;
        double maxLysVidde220 = 461;
        double maxLysVidde245 = 541;

        if (lysVidde > maxLysVidde195)
            raftHeight = 22;
        if (lysVidde > maxLysVidde220)
            raftHeight = 24.5;
        if (lysVidde > maxLysVidde245)
            raftHeight = 24.5;

        return raftHeight;
    }

    public int getAmountOfRaftersTrapez(Carport carport) {
        double length = carport.getLength();
        double rafterDistance = getDistanceRafters(carport);
        return (int) Math.ceil(length / rafterDistance) + 1;
    }

    public double getDistanceRafters(Carport carport) {
        double length = carport.getLength();
        double raftHeight = getRafterHeightFromLysVidde(carport);
        double lysvidde = getRafterLysViddeTrapez(carport) / 100;
        double a;
        double b;
        double maxDistBetweenRaftersTrapez = 0.7;

        if (raftHeight == 24.5) {
            if (lysvidde <= 5.41 && lysvidde > 4.82) {
                a = (0.6 - 0.4) / (4.82 - 5.41);
                b = -a * 5.41 + 0.4;
                maxDistBetweenRaftersTrapez = a * lysvidde + b;
            }

            if (lysvidde <= 4.82 && lysvidde > 4.61) {
                a = (0.7 - 0.6) / (4.62 - 4.82);
                b = -a * 4.82 + 0.6;
                maxDistBetweenRaftersTrapez = a * lysvidde + b;
            }
        }

        if (raftHeight == 22.0) {
            if (lysvidde <= 4.61 && lysvidde > 4.34) {
                a = (0.6 - 0.5) / (4.34 - 4.61);
                b = -a * 4.61 + 0.5;
                maxDistBetweenRaftersTrapez = a * lysvidde + b;
            }

            if (lysvidde <= 4.34 && lysvidde > 4.095) {
                a = (0.7 - 0.6) / (4.155 - 4.34);
                b = -a * 4.34 + 0.6;
                maxDistBetweenRaftersTrapez = a * lysvidde + b;
            }
        }

        if (raftHeight == 19.5) {
            if (lysvidde <= 4.095 && lysvidde > 3.85) {
                a = (0.6 - 0.5) / (3.85 - 4.095);
                b = -a * 4.095 + 0.5;
                maxDistBetweenRaftersTrapez = a * lysvidde + b;
            }

            if (lysvidde <= 3.85 && lysvidde > 3.685) {
                a = (0.7 - 0.6) / (3.685 - 3.85);
                b = -a * 3.85 + 0.6;
                maxDistBetweenRaftersTrapez = a * lysvidde + b;
            }
        }

        maxDistBetweenRaftersTrapez = maxDistBetweenRaftersTrapez * 100;

        if (lysvidde <= 3.685) {
            maxDistBetweenRaftersTrapez = 70.00;
        }
        double lengthDivMaxDist = Math.ceil(length / maxDistBetweenRaftersTrapez);

        return length / lengthDivMaxDist;
    }

    public int getRafterLength(Carport carport) {
        int rafterLength = 720;

        if (carport.getWidth() < 660)
            rafterLength = 660;
        if (carport.getWidth() < 600)
            rafterLength = 600;
        if (carport.getWidth() < 540)
            rafterLength = 540;
        if (carport.getWidth() < 480)
            rafterLength = 480;
        if (carport.getWidth() < 420)
            rafterLength = 420;
        if (carport.getWidth() < 360)
            rafterLength = 360;
        if (carport.getWidth() < 300)
            rafterLength = 300;

        return rafterLength;
    }


    // Trapez

    public int getAmountTrapezRow(Carport carport) {
        double length = carport.getLength();
        int amount = 1;
        if (length > 570)
            amount = 2;
        return amount;
    }

    public int getLengthOfTrapez(Carport carport) {
        double length = carport.getLength();
        int buffer = 20;
        int trapezLength = 240;

        double minDistTrapez = length + buffer;
        int amountTrapezRow = getAmountTrapezRow(carport);

        if (amountTrapezRow == 2) {
            minDistTrapez = (length + buffer) / 2;
        }

        if (minDistTrapez < 600)
            trapezLength = 600;
        if (minDistTrapez < 480)
            trapezLength = 480;
        if (minDistTrapez < 420)
            trapezLength = 420;
        if (minDistTrapez < 360)
            trapezLength = 360;
        if (minDistTrapez < 300)
            trapezLength = 300;

        return trapezLength;
    }

    public int getAmountOfTrapezAcross(Carport carport) {
        double width = carport.getWidth();
        return (int) Math.ceil(width / 100);
    }

    public int getTotalAmountOfTrapez(Carport carport) {
        int trapezRow = getAmountTrapezRow(carport);
        int trapezAcross = getAmountOfTrapezAcross(carport);

        return trapezRow * trapezAcross;
    }

    // Remme
    public int getAmountOfRems(Carport carport) {
        int amountOfRems = 2;

        if (carport.getLength() > 720) {
            amountOfRems = 4;
        }
        return amountOfRems;
    }

    public int getLengthOfRems(Carport carport) {
        int amountOfRems = getAmountOfRems(carport);
        int remLength = carport.getLength();

        if (amountOfRems > 2) {
            double idealRemLength = carport.getLength() / amountOfRems;

            if (idealRemLength > 370 && idealRemLength < 420)
                remLength = 420;
        }
        return remLength;
    }


    // Stolper
    public int getAmountOfPosts(Carport carport) {
        double squareMeters = (carport.getLength() / 100) * (carport.getWidth() / 100);
        double postsPreRounded = squareMeters / 5.5;
        int totalPosts = 4;

        if (postsPreRounded > 4 && postsPreRounded <= 6)
            totalPosts = 6;
        else if (postsPreRounded > 6 && postsPreRounded <= 8)
            totalPosts = 8;
        else if (postsPreRounded > 8 && postsPreRounded <= 10)
            totalPosts = 10;
        else if (postsPreRounded > 10 && postsPreRounded <= 12)
            totalPosts = 12;
        else if (postsPreRounded > 12 && postsPreRounded <= 14)
            totalPosts = 14;

        // TODO: Figure out a better way to do the calculations below
        if (carport.getShed() != null) {
            if (carport.getShed().getLength() == carport.getLength()) {
                // skur fylder hele langside        + 3
                totalPosts += 3;
            }

            else if (carport.getShed().getLength() == carport.getLength() / 2) {
                // skur fylder halvdelen af langsiden       + 2
                totalPosts += 2;
            }

            else if (carport.getShed().getWidth() == carport.getWidth()) {
                // skur fylder hele badside     + 4
                totalPosts += 4;
            }
        }

        return totalPosts;
    }

    // Stern brædder
    public int getAmountOfStern(Carport carport) {
        int amountOfUnderStern = 4;

        if (carport.getLength() > 600)
            amountOfUnderStern += 2;
        if (carport.getWidth() > 600)
            amountOfUnderStern += 2;

        return amountOfUnderStern;
    }

    public int getLengthOfStern(Carport carport) {
        int amountOfUnderStern = getAmountOfStern(carport);
        int lengthOfUnderStern = 300;
        int chosenSide = 0;

        if (amountOfUnderStern == 4) {
            if (carport.getWidth() >= carport.getLength())
                chosenSide = carport.getWidth();
            else
                chosenSide = carport.getLength();
        } else if (amountOfUnderStern == 6 || amountOfUnderStern == 8) {
            if (carport.getWidth() <= carport.getLength())
                chosenSide = carport.getWidth();
            else
                chosenSide = carport.getLength();
        }

        if (chosenSide <= 600)
            lengthOfUnderStern = 600;
        if (chosenSide < 540)
            lengthOfUnderStern = 540;
        if (chosenSide < 480)
            lengthOfUnderStern = 480;
        if (chosenSide < 420)
            lengthOfUnderStern = 420;
        if (chosenSide < 360)
            lengthOfUnderStern = 360;
        if (chosenSide < 300)
            lengthOfUnderStern = 300;

        return lengthOfUnderStern;
    }

    public double getSquareMeterShedCladdingKlink(Carport carport) {
        double shedLength = (double) carport.getShed().getLength() / 100;
        double shedWidth = (double) carport.getShed().getWidth() / 100;
        double shedHeight = (double) carport.getHeight() / 100;

        double side1 = ((shedLength * shedHeight) * 2) * 1.3;
        double side2 = ((shedWidth * shedHeight) * 2) * 1.3;
        return side1 + side2;
    }

    public double getTotalMetersShedCladdingKlink(Carport carport) {
        double totalSquareMeters = getSquareMeterShedCladdingKlink(carport);
        return (totalSquareMeters * 9.09);
    }

    public int[] getCladdingKlink(Carport carport) {
        double totalMeters = getTotalMetersShedCladdingKlink(carport);

        HashMap<Integer, Double> remainHash = new HashMap<>();
        remainHash.put(300, totalMeters % 3);
        remainHash.put(360, totalMeters % 3.6);
        remainHash.put(390, totalMeters % 3.9);
        remainHash.put(420, totalMeters % 4.2);
        remainHash.put(450, totalMeters % 4.5);
        remainHash.put(480, totalMeters % 4.8);
        remainHash.put(540, totalMeters % 5.4);


        double leastRemains = 9999999;
        int leastKey = 0;

        for (Map.Entry<Integer, Double> remains : remainHash.entrySet()) {
            Integer key = remains.getKey();
            Double value = remains.getValue();
            if (value < leastRemains) {
                leastRemains = value;
                leastKey = key;
            }
        }
        double totalBoards = totalMeters / ((double) leastKey / 100);

        return new int[]{leastKey, (int)totalBoards + 1};
    }

}
