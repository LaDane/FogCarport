package business.carportCalc;

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

}
