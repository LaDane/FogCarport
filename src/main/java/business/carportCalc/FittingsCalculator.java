package business.carportCalc;

import business.entities.Carport;
import business.entities.Order;
import business.entities.OrderLine;
import business.entities.materials.Material;
import business.services.MaterialFacade;

import java.util.ArrayList;
import java.util.List;

public class FittingsCalculator {

    MaterialFacade materialFacade;

    public FittingsCalculator(MaterialFacade materialFacade) {
        this.materialFacade = materialFacade;
    }

    public List<OrderLine> calculateCarportMaterials(List<OrderLine> orderList, Carport carport) {

        OrderLine rafterOL = null;
        OrderLine postOL = null;
        OrderLine claddingOL = null;

        for (OrderLine ol : orderList) {
            if (ol.getMaterial().getName().equals("Spærtræ")) {
                rafterOL = ol;
                break;
            }
        }

        for (OrderLine ol : orderList) {
            if (ol.getMaterial().getMaterialId() == 6) {
                postOL = ol;
            }
            if (ol.getMaterial().getMaterialId() == 26) {
                claddingOL = ol;
            }
        }

        List<OrderLine> fittingsList = new ArrayList<>();

        if (rafterOL != null && postOL != null && claddingOL != null) {

            fittingsList.add(getRafterFittings(rafterOL));
            fittingsList.add(getRafterFittingScrews(rafterOL));
            fittingsList.add(getTrapezScres(carport));
            fittingsList.add(getBuckleBand());
            fittingsList.add(getScrews60(carport));
            fittingsList.add(getPostBolts(postOL));
            fittingsList.add(getPostSquareBrackets(postOL));

            if (carport.getShed() != null) {
                fittingsList.add(getScrewsCladding(claddingOL));
                fittingsList.add(getDoorGrip());
                fittingsList.add(getDoorHinges());
            }
        }
        return fittingsList;
    }

    public double calculateFittingsPrice(List<OrderLine> orderList, Carport carport) {
        List<OrderLine> orderLines = calculateCarportMaterials(orderList, carport);
        double totalPrice = 0;
        for (OrderLine orderLine : orderLines) {
            totalPrice += orderLine.getPrice();
        }
        return totalPrice;
    }

    private OrderLine getRafterFittings(OrderLine orderLine) {

        int rafterAmmount = orderLine.getAmount();
        int fittingsAmmount = rafterAmmount * 2;

        Material fittingsMat = materialFacade.getSpecificMaterial(30); // Universalbeslag
        double fittingPrice = fittingsMat.getPrice() * fittingsAmmount;

        return new OrderLine(fittingsMat, -1, fittingsAmmount, fittingPrice);

    }

    private OrderLine getRafterFittingScrews(OrderLine orderLine) {

        OrderLine rafterFittingOL = getRafterFittings(orderLine);
        int numberOfFittings = rafterFittingOL.getAmount();
        int numberOfScrews = numberOfFittings * 10;
        int numberOfBoxes = (int) Math.ceil(numberOfScrews / 250.0);

        Material fittingScrewsMat = materialFacade.getSpecificMaterial(9);
        double fittingScrewsPrice = fittingScrewsMat.getPrice() * numberOfBoxes;

        return new OrderLine(fittingScrewsMat, -1, numberOfBoxes, fittingScrewsPrice);
    }

    private OrderLine getTrapezScres(Carport carport) {

        int getArea = (carport.getLength() / 100) * (carport.getWidth() / 100);
        int getScrewAmmount = getArea * 12;

        int numberOfBoxes = (int) Math.ceil(getScrewAmmount / 50.0);

        Material fittingsMat = materialFacade.getSpecificMaterial(31);
        double screwPrice = fittingsMat.getPrice() * numberOfBoxes;

        return new OrderLine(fittingsMat, -1, numberOfBoxes, screwPrice);
    }

    private OrderLine getBuckleBand() {

        Material buckleBandMat = materialFacade.getSpecificMaterial(7);
        double buckleBandPrice = buckleBandMat.getPrice() * 2;

        return new OrderLine(buckleBandMat, -1, 2, buckleBandPrice);
    }

    private OrderLine getScrews60(Carport carport) {

        int carportCircumference = carport.getLength() + carport.getWidth() * 2;
        int numberOfScrews = (carportCircumference / 60) + 1;
        int numberOfBoxes = (int) Math.ceil(numberOfScrews / 200.0);

        Material screwMat60 = materialFacade.getSpecificMaterial(11);
        double screwPrice60 = screwMat60.getPrice() * numberOfBoxes;

        return new OrderLine(screwMat60, -1, numberOfBoxes, screwPrice60);
    }

    private OrderLine getPostBolts(OrderLine orderLine) {

        int postAmount = orderLine.getAmount();
        int boltAmount = postAmount * 2;

        Material postBoltMat = materialFacade.getSpecificMaterial(13);
        double postBoltPrice = postBoltMat.getPrice() * boltAmount;

        return new OrderLine(postBoltMat, -1, boltAmount, postBoltPrice);
    }

    private OrderLine getPostSquareBrackets(OrderLine orderLine) {

        OrderLine postBolts = getPostBolts(orderLine);
        int squareBracketAmount = postBolts.getAmount();

        Material squareBracketMat = materialFacade.getSpecificMaterial(14);
        double squareBracketPrice = squareBracketMat.getPrice() * squareBracketAmount;

        return new OrderLine(squareBracketMat, -1, squareBracketAmount, squareBracketPrice);
    }

    private OrderLine getScrewsCladding(OrderLine orderLine) {

        int boardAmount = orderLine.getAmount();
        int screwCladdingAmount = boardAmount * 8;
        int numberOfBoxes = (int) Math.ceil(screwCladdingAmount / 200.0);

        Material screwCladdingMat = materialFacade.getSpecificMaterial(10);
        double screwCladdingPrice = screwCladdingMat.getPrice() * numberOfBoxes;

        return new OrderLine(screwCladdingMat, -1, numberOfBoxes, screwCladdingPrice);
    }

    // Kun hvis der er skur
    private OrderLine getDoorGrip() {

        Material doorGripMat = materialFacade.getSpecificMaterial(15);
        return new OrderLine(doorGripMat, -1, 1, doorGripMat.getPrice());
    }

    private OrderLine getDoorHinges() {
        Material doorHingesMat = materialFacade.getSpecificMaterial(16);
        return new OrderLine(doorHingesMat, -1, 2, doorHingesMat.getPrice() * 2);
    }

    // TODO løsholter og vinkelbeslag til 1 på 2 beklædning
}
