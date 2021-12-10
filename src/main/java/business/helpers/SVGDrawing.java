package business.helpers;

import business.carportCalc.Calculator;
import business.entities.Carport;
import business.entities.SVG;

public class SVGDrawing {



    public static String getSVGDrawing(Carport carport){
        Calculator calculator = new Calculator();


        // Carport


        double poleDim = 9.5;  // 95x95
        double rafterDim = 4.5; // 195x45

        int svgWidth = 50;  // OBS: only the outer svg
        int svgHeight = 50; // OBS: only the outer svg

        int margin = 50; // Margin Left and Under the drawing with the measurements

        int drawingWidth = carport.getLength() + margin;
        int drawingHeight = carport.getWidth() + margin * 2;


        // inner svg

        SVG innerSVG = new SVG(margin, margin, "0 0 " + drawingWidth + " " + drawingHeight, 100, 100 );

        // Frame

        innerSVG.addRect(0, 0, carport.getWidth(), carport.getLength());

        // Rems

        innerSVG.addRect(0, 30, rafterDim, carport.getLength());
        innerSVG.addRect(0, carport.getWidth()-30, rafterDim, carport.getLength());


        // Poles

        double poleOffset = rafterDim/2;

        int amountOfPoles = calculator.getAmountOfPosts(carport);

        if(amountOfPoles == 4){
            innerSVG.addRect(carport.getLength() * 0.15, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(carport.getLength() - 30, 30 - poleOffset, poleDim, poleDim);

            innerSVG.addRect(carport.getLength() * 0.15, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(carport.getLength() - 30, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
        }


        if(amountOfPoles == 6) {

            double firstPole = carport.getLength() * 0.15;
            double lastPole = carport.getLength() - 30;
            double midPole = (firstPole + lastPole) / 2;


            innerSVG.addRect(firstPole, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(midPole, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(lastPole, 30 - poleOffset, poleDim, poleDim);

            innerSVG.addRect(firstPole, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(midPole, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(lastPole, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
        }

        if(amountOfPoles == 8 && carport.getShed() == null) {
            double firstPole = carport.getLength() * 0.15;
            double lastPole = carport.getLength() - 30;
            double midPole1 = (lastPole - firstPole) * 0.33 + firstPole;
            double midPole2 = (lastPole - firstPole) * 0.66 + firstPole;


            innerSVG.addRect(firstPole, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(midPole1, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(midPole2, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(lastPole, 30 - poleOffset, poleDim, poleDim);

            innerSVG.addRect(firstPole, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(midPole1, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(midPole2, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(lastPole, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
        }



        // Rafters

        int amountRafter = calculator.getAmountOfRaftersTrapez(carport);

        double spaceBetween = (carport.getLength() - rafterDim) / amountRafter;

        for (int x = 0; x < amountRafter + 1; x++)
        {
            innerSVG.addRect(spaceBetween * x, 0, carport.getWidth(), rafterDim);
        }
        innerSVG.addRect(carport.getLength() - rafterDim, 0, carport.getWidth(), rafterDim);


        // hulbånd

        innerSVG.addDashLine(50,30,carport.getLength()-50,carport.getWidth()-30+rafterDim);
        innerSVG.addDashLine(50,carport.getWidth()-30+rafterDim,carport.getLength()-50,30);


        // Outer svg

        SVG svg = new SVG(0, 0, "0 0 " + drawingWidth + " " + drawingHeight, 50 , 50 );


        // Arrows with measurements

        svg.addHorizontalArrowWithText(carport.getLength(), carport.getWidth(), margin);
        svg.addVerticalArrowWithText(carport.getWidth(), margin);


       svg.addHorizontalMeasurement(margin, (double) margin / 2, margin + spaceBetween, (double) margin / 2, (int)spaceBetween);


        svg.addSvg(innerSVG);



        return svg.toString();


    }


}
