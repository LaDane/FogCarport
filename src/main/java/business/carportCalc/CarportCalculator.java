package business.carportCalc;

import business.entities.Carport;
import business.entities.OrderLine;
import business.entities.materials.Material;
import business.services.MaterialFacade;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CarportCalculator extends Calculator {

    private MaterialFacade materialFacade;
//    private List<OrderLine> completeOrder;

    public CarportCalculator(MaterialFacade materialFacade) {
        this.materialFacade = materialFacade;
    }

    public List<OrderLine> calculateCarportMaterials(Carport carport) {
        List<OrderLine> completeOrder = new ArrayList<>();

        completeOrder.add(getRafterOrder(carport));                 // Spær
        completeOrder.add(getTrapezOrder(carport));                 // Trapez
        completeOrder.add(getRemOrder(carport));                    // Remme
        completeOrder.add(getPostOrder(carport));                   // Stolper
        completeOrder.add(getSternOrder(carport, false));     // Stern under
        completeOrder.add(getSternOrder(carport, true));      // Stern over
        if (carport.getShed() != null) {
            completeOrder.add(getShedCladdingOrder(carport));       // Beklædning
        }

        return completeOrder;
    }

    public double calculateCarportPrice(Carport carport) {
        List<OrderLine> orderLines = calculateCarportMaterials(carport);
        double totalPrice = 0;
        for (OrderLine orderLine : orderLines) {
            totalPrice += orderLine.getPrice();
        }
        return totalPrice;
    }

    private OrderLine getRafterOrder(Carport carport) {
        double rafterHeight = getRafterHeightFromLysVidde(carport);
        int rafterLength = getRafterLength(carport);
        int rafterAmount = getAmountOfRaftersTrapez(carport);

        Material rafterMat = materialFacade.getSpecificMaterial(5); // 45x195
        if (rafterHeight == 22)
            rafterMat = materialFacade.getSpecificMaterial(28); // 45x220
        else if (rafterHeight == 24.5)
            rafterMat = materialFacade.getSpecificMaterial(29); // 45x245

        double orderLinePrice = (double) rafterLength / 100 * rafterAmount * rafterMat.getPrice();

        return new OrderLine(rafterMat, rafterLength, rafterAmount, orderLinePrice);
    }

    private OrderLine getTrapezOrder(Carport carport) {
        Material trapezRoof = materialFacade.getSpecificMaterial(21);
        int lengthTrapez = getLengthOfTrapez(carport);
        int amountOfTrapez = getTotalAmountOfTrapez(carport);
        double priceTrapez = trapezRoof.getPrice() * lengthTrapez / 100 * amountOfTrapez;

        return new OrderLine(trapezRoof, lengthTrapez, amountOfTrapez, priceTrapez);
    }

    private OrderLine getRemOrder(Carport carport) {
        int remAmount = getAmountOfRems(carport);
        int remLength = getLengthOfRems(carport);
        Material remMat = materialFacade.getSpecificMaterial(5); // 45x195
        double price = (double) remLength / 100 * remAmount * remMat.getPrice();

        return new OrderLine(remMat, remLength, remAmount, price);
    }

    private OrderLine getPostOrder(Carport carport) {
        int postAmount = getAmountOfPosts(carport);
        int postLength = carport.getHeight() + 100;
        Material postMat = materialFacade.getSpecificMaterial(6); // 97x97
        double price = (double) postLength / 100 * postAmount * postMat.getPrice();

        return new OrderLine(postMat, postLength, postAmount, price);
    }

    private OrderLine getSternOrder(Carport carport, boolean isOver) {
        int sternAmount = getAmountOfStern(carport);
        int sternLength = getLengthOfStern(carport);
        Material sternMat = materialFacade.getSpecificMaterial(1);  // Under
        if (isOver)
            sternMat = materialFacade.getSpecificMaterial(2);  // Over
        double price = (double) sternLength / 100 * sternAmount * sternMat.getPrice();

        return new OrderLine(sternMat, sternLength, sternAmount, price);
    }

    private OrderLine getShedCladdingOrder(Carport carport) {
        int[] shedCladding = getCladdingKlink(carport);
        int claddingAmount = shedCladding[1];
        int claddingLength = shedCladding[0];
        Material claddingMat = materialFacade.getSpecificMaterial(26);   // klink
        double price = (double) claddingLength / 100 * claddingAmount * claddingMat.getPrice();

        return new OrderLine(claddingMat, claddingLength, claddingAmount, price);
    }


//
//
//    public void assemblyTrapezRoof(Carport carport) {
//        double lysvidde = getRafterLysViddeTrapez(carport);
//
//        // tag plader
//        Material trapezRoof = materialFacade.getSpecificMaterial(21);
//        int lengthTrapez = getLengthOfTrapez(carport.getLength());
//        int amountOfTrapez = getTotalAmountOfTrapez(carport.getLength(), carport.getWidth());
//        double priceTrapez = trapezRoof.getPrice() * lengthTrapez / 100 * amountOfTrapez;
//
//        OrderLine trapez = new OrderLine(trapezRoof, lengthTrapez, amountOfTrapez, priceTrapez);
//        completeOrder.add(trapez);
//
//        // Remme
////        double remHeight = getRafterHeightFromLysVidde(lysvidde);
//
//        Material remMaterial = materialFacade.getSpecificMaterial(5); // 45x195
//
////        Material remMaterial = null;
////        if (remHeight == 19.5)
////            remMaterial = materialFacade.getSpecificMaterial(5); // 45x195
////        else if (remHeight == 22)
////            remMaterial = materialFacade.getSpecificMaterial(28); // 45x220
////        else if (remHeight == 24.5)
////            remMaterial = materialFacade.getSpecificMaterial(29); // 45x245
//
//        int remLength = carport.getLength();
//        double priceRem = remMaterial.getPrice() * remLength / 100 * 2;
//
//        OrderLine rems = new OrderLine(remMaterial, remLength, 2, priceRem);
//        completeOrder.add(rems);
//
//
//        // Spær
//        // Sterner
//        // Skruer og beslag
//    }




}
