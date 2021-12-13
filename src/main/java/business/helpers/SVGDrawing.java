package business.helpers;

import business.carportCalc.Calculator;
import business.entities.Carport;
import business.entities.SVG;

public class SVGDrawing {


    public static String getSVGDrawingTop(Carport carport) {
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
        SVG innerSVG = new SVG(margin, margin, "0 0 " + drawingWidth + " " + drawingHeight, 100, 100);

        // Frame
        innerSVG.addRect(0, 0, carport.getWidth(), carport.getLength());

        // Rems
        innerSVG.addRect(0, 30, rafterDim, carport.getLength());
        innerSVG.addRect(0, carport.getWidth() - 30, rafterDim, carport.getLength());


        // Poles
        double poleOffset = rafterDim / 2;
        int amountOfPoles = calculator.getAmountOfPosts(carport);

        if (amountOfPoles == 4) {
            innerSVG.addRect(carport.getLength() * 0.15, 30 - poleOffset, poleDim, poleDim);
            innerSVG.addRect(carport.getLength() - 30, 30 - poleOffset, poleDim, poleDim);

            innerSVG.addRect(carport.getLength() * 0.15, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
            innerSVG.addRect(carport.getLength() - 30, carport.getWidth() - (30 + poleOffset), poleDim, poleDim);
        }

        if (amountOfPoles == 6 && carport.getShed() == null) {

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

        if (amountOfPoles == 8 && carport.getShed() == null) {
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

        // TODO: Add shed functionality


        // Rafters
        int amountRafter = calculator.getAmountOfRaftersTrapez(carport);

        double spaceBetween = (carport.getLength() - rafterDim) / amountRafter;

        for (int x = 0; x < amountRafter + 1; x++) {
            innerSVG.addRect(spaceBetween * x, 0, carport.getWidth(), rafterDim);
        }
        innerSVG.addRect(carport.getLength() - rafterDim, 0, carport.getWidth(), rafterDim);


        // hulbånd
        innerSVG.addDashLine(50, 30, carport.getLength() - 50, carport.getWidth() - 30 + rafterDim);
        innerSVG.addDashLine(50, carport.getWidth() - 30 + rafterDim, carport.getLength() - 50, 30);


        // Outer svg
        SVG svg = new SVG(0, 0, "0 0 " + drawingWidth + " " + drawingHeight, 50, 50);


        // Arrows with measurements
        svg.addHorizontalArrowWithText(carport.getLength(), carport.getWidth(), margin);
        svg.addVerticalArrowWithText(carport.getWidth(), margin);

        svg.addHorizontalMeasurement(margin, (double) margin / 2, margin + spaceBetween, (double) margin / 2, (int) spaceBetween);

        svg.addSvg(innerSVG);
        return svg.toString();
    }

    public static String getSVGDrawingSide(Carport carport) {
        Calculator calculator = new Calculator();

        // Carport
        double postDim = 9.5;  // 95x95
        double sternInner = 20;
        double sternOuter = 12.5;

        // Margins      OBS: margin from outer SVG to inner SVG
        int marginTop = 55;
        int marginLeft = 100;
        int marginBot = 150;
        int marginRight = 50;

        // Inner SVG viewport
        int drawingWidth = carport.getLength() + marginLeft + marginRight;
        int drawingHeight = carport.getHeight() + marginTop + marginBot;

        // inner svg
        SVG innerSVG = new SVG(marginLeft, marginTop, "0 0 " + drawingWidth + " " + drawingHeight, 100, 100);

        // Posts
        int postAmount = calculator.getAmountOfPosts(carport);
        double postStartY = 30;
        double postEndY = 220;

        double firstPostX = carport.getLength() * 0.15;
        double lastPostX = carport.getLength() - 30;
        double midPost1 = -1;
        double midPost2 = -1;

        if (postAmount == 4) {
            innerSVG.addRect(firstPostX, postStartY, postEndY, postDim);
            innerSVG.addRect(lastPostX, postStartY, postEndY, postDim);
        }
        else if (postAmount == 6 && carport.getShed() == null) {
            midPost1 = (firstPostX + lastPostX) / 2;

            innerSVG.addRect(firstPostX, postStartY, postEndY, postDim);
            innerSVG.addRect(midPost1, postStartY, postEndY, postDim);
            innerSVG.addRect(lastPostX, postStartY, postEndY, postDim);
        }
        else if (postAmount == 8 && carport.getShed() == null) {
            midPost1 = (lastPostX - firstPostX) * 0.33 + firstPostX;
            midPost2 = (lastPostX - firstPostX) * 0.66 + firstPostX;

            innerSVG.addRect(firstPostX, postStartY, postEndY, postDim);
            innerSVG.addRect(midPost1, postStartY, postEndY, postDim);
            innerSVG.addRect(midPost2, postStartY, postEndY, postDim);
            innerSVG.addRect(lastPostX, postStartY, postEndY, postDim);
        }

        // Roof
        innerSVG.addRectRotate(0, 0, sternOuter, carport.getLength());
        innerSVG.addRectRotate(0, 10, sternInner, carport.getLength());

        // Ground line
        innerSVG.addLine(0, carport.getHeight(), carport.getLength(), carport.getHeight());

        // Outer svg
        SVG svg = new SVG(0, 0, "0 0 " + drawingWidth + " " + drawingHeight, 50, 50);

        // TODO: Add all the required measurements
        double horizontalBotY = marginTop + carport.getHeight() + ((double) marginBot / 2);
        svg.addHorizontalMeasurement(marginLeft, horizontalBotY, marginLeft + firstPostX, horizontalBotY, (int) firstPostX);

        svg.addDashLine(marginLeft, marginTop + carport.getHeight(), marginLeft, horizontalBotY + 15);
        svg.addDashLine(marginLeft + firstPostX, marginTop + carport.getHeight() + 30, marginLeft + firstPostX, horizontalBotY + 15);

        if (postAmount == 4) {
            svg.addHorizontalMeasurement(marginLeft + firstPostX, horizontalBotY, marginLeft + lastPostX, horizontalBotY, (int) (lastPostX - firstPostX));
        }
        else if (postAmount == 6 && carport.getShed() == null) {
            svg.addHorizontalMeasurement(marginLeft + firstPostX, horizontalBotY, marginLeft + midPost1, horizontalBotY, (int) (midPost1 - firstPostX));
            svg.addHorizontalMeasurement(marginLeft + midPost1, horizontalBotY, marginLeft + lastPostX, horizontalBotY, (int) (lastPostX - midPost1));
            svg.addDashLine(marginLeft + midPost1, marginTop + carport.getHeight() + 30, marginLeft + midPost1, horizontalBotY + 15);
        }
        else if (postAmount == 8 && carport.getShed() == null) {
            svg.addHorizontalMeasurement(marginLeft + firstPostX, horizontalBotY, marginLeft + midPost1, horizontalBotY, (int) (midPost1 - firstPostX));
            svg.addHorizontalMeasurement(marginLeft + midPost1, horizontalBotY, marginLeft + midPost2, horizontalBotY, (int) (midPost2 - midPost1));
            svg.addHorizontalMeasurement(marginLeft + midPost2, horizontalBotY, marginLeft + lastPostX, horizontalBotY, (int) (lastPostX - midPost2));

            svg.addDashLine(marginLeft + midPost1, marginTop + carport.getHeight() + 30, marginLeft + midPost1, horizontalBotY + 15);
            svg.addDashLine(marginLeft + midPost2, marginTop + carport.getHeight() + 30, marginLeft + midPost2, horizontalBotY + 15);
        }


        svg.addDashLine(marginLeft + lastPostX, marginTop + carport.getHeight() + 30, marginLeft + lastPostX, horizontalBotY + 15);
        svg.addHorizontalMeasurement(marginLeft + lastPostX, horizontalBotY, marginLeft + carport.getLength(), horizontalBotY, 30);
        svg.addDashLine(marginLeft + carport.getLength(), marginTop + carport.getHeight(), marginLeft + carport.getLength(), horizontalBotY + 15);

        // Vertical measurements    Left side
        svg.addVerticalMeasurement(marginLeft - 20, marginTop + 33, marginLeft - 20, marginTop + carport.getHeight(), carport.getHeight() - 30);
        svg.addVerticalMeasurement(marginLeft - 50, marginTop, marginLeft - 50, marginTop + carport.getHeight(), carport.getHeight());

        // Vertical measurement     Right side
        double rightSideHeight = carport.getHeight() - ((double) carport.getLength() / 100);
        svg.addVerticalMeasurement(marginLeft + carport.getLength() + 30, marginTop + 10, marginLeft + carport.getLength() + 30, marginTop + carport.getHeight(), (int) rightSideHeight);

        svg.addSvg(innerSVG);
        return svg.toString();
    }

    public static String getShedPlacementSVG(int type) {
        SVG svg = new SVG(0, 0, "0 0 " + 100 + " " + 150, 25, 25);

        svg.addRect(0, 0, 150, 100);
        switch (type) {
            case 1: svg.addRectDash(2, 2, 50, 50); break;
            case 2: svg.addRectDash(2, 2, 50, 96); break;
            case 3: svg.addRectDash(50, 2, 50, 48); break;
        }

        return svg.toString();
    }
}
