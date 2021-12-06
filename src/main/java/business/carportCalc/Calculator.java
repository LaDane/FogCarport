package business.carportCalc;

import business.entities.Carport;

public class Calculator {

    // Carport Spær

    public double getRafterLysViddeTrapez(double width) {
        double distanceFromRemToEdge = 39.5;
        return width - (distanceFromRemToEdge * 2);
    }

    public double getRafterHeightFromLysVidde(double lysVidde) {
        double raftHeight = 19.5;

        double maxLysVidde195 = 409.5;
        double maxLysVidde220 = 461;
        double maxLysVidde245 = 541;

        if (lysVidde > maxLysVidde195)
            raftHeight = 22;
        if (lysVidde > maxLysVidde220)
            raftHeight = 24.5;
        if (lysVidde > maxLysVidde245)
            raftHeight = -1;

        return raftHeight;
    }

    public int getAmountOfRaftersTrapez(double length, double rafterDistance) {
        return (int) Math.ceil(length / rafterDistance) + 1;
    }

    public double getDistanceRafters(double lysvidde, double raftHeight, double length) {
        lysvidde = lysvidde / 100;
        double a;
        double b;
        double bjælkeAfstand = 0;

        if (raftHeight == 24.5) {
            if (lysvidde <= 5.41 && lysvidde > 4.82) {
                a = (0.6 - 0.4) / (4.82 - 5.41);
                b = -a * 5.41 + 0.4;
                bjælkeAfstand = a * lysvidde + b;
            }

            if (lysvidde <= 4.82 && lysvidde > 4.61) {
                a = (0.7 - 0.6) / (4.62 - 4.82);
                b = -a * 4.82 + 0.6;
                bjælkeAfstand = a * lysvidde + b;
            }
        }

        if (raftHeight == 22.0) {
            if (lysvidde <= 4.61 && lysvidde > 4.34) {
                a = (0.6 - 0.5) / (4.34 - 4.61);
                b = -a * 4.61 + 0.5;
                bjælkeAfstand = a * lysvidde + b;
            }

            if (lysvidde <= 4.34 && lysvidde > 4.095) {
                a = (0.7 - 0.6) / (4.155 - 4.34);
                b = -a * 4.34 + 0.6;
                bjælkeAfstand = a * lysvidde + b;
            }
        }

        if (raftHeight == 19.5) {
            if (lysvidde <= 4.095 && lysvidde > 3.85) {
                a = (0.6 - 0.5) / (3.85 - 4.095);
                b = -a * 4.095 + 0.5;
                bjælkeAfstand = a * lysvidde + b;
            }

            if (lysvidde <= 3.85 && lysvidde > 3.685) {
                a = (0.7 - 0.6) / (3.685 - 3.85);
                b = -a * 3.85 + 0.6;
                bjælkeAfstand = a * lysvidde + b;
            }
        }

        if (lysvidde <= 3.685) {
            double maxDistBetweenRaftersTrapez = 70.00;
            double lengthDivMaxDist = Math.ceil(length / maxDistBetweenRaftersTrapez);

            double rafterDist = length / lengthDivMaxDist;
            return rafterDist;

        }
        return bjælkeAfstand * 100;
    }


    // Trapez

    public int getAmountTrapezRow(double length) {
        int amount = 1;
        if (length > 570)
            amount = 2;
        return amount;
    }

    public int getLengthOfTrapez(double length) {
        int buffer = 20;
        int trapezLength = 240;

        double minDistTrapez = length + buffer;
        int amountTrapezRow = getAmountTrapezRow(length);

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

    public int getAmountOfTrapezAcross(double width) {
        return (int) Math.ceil(width / 100);
    }

    public int getTotalAmountOfTrapez(double length, double width) {
        int trapezRow = getAmountTrapezRow(length);
        int trapezAcross = getAmountOfTrapezAcross(width);

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
    public int getAmountOfUnderStern(Carport carport) {
        int amountOfUnderStern = 4;

        if (carport.getLength() > 600)
            amountOfUnderStern += 2;
        if (carport.getWidth() > 600)
            amountOfUnderStern += 2;

        return amountOfUnderStern;
    }

    public int getLengthOfUnderStern(Carport carport) {
        // TODO: Finish this
        return 0;
    }

}
