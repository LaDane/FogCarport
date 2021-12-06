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
    private List<OrderLine> completeOrder;

    public CarportCalculator(MaterialFacade materialFacade) {
        this.materialFacade = materialFacade;
    }

    public List<OrderLine> calculateCarport(Carport carport) {
        completeOrder = new ArrayList<>();

        // Beregn tag
        assemblyTrapezRoof(carport);


        // Beregn stolper

        // Beregn skur


        return completeOrder;
    }


    public void assemblyTrapezRoof(Carport carport) {
        double lysvidde = getRafterLysViddeTrapez(carport.getWidth());

        // tag plader
        Material trapezRoof = materialFacade.getSpecificMaterial(21);
        int lengthTrapez = getLengthOfTrapez(carport.getLength());
        int amountOfTrapez = getTotalAmountOfTrapez(carport.getLength(), carport.getWidth());
        double priceTrapez = trapezRoof.getPrice() * lengthTrapez / 100 * amountOfTrapez;

        OrderLine trapez = new OrderLine(trapezRoof, lengthTrapez, amountOfTrapez, priceTrapez);
        completeOrder.add(trapez);

        // Remme
//        double remHeight = getRafterHeightFromLysVidde(lysvidde);

        Material remMaterial = materialFacade.getSpecificMaterial(5); // 45x195

//        Material remMaterial = null;
//        if (remHeight == 19.5)
//            remMaterial = materialFacade.getSpecificMaterial(5); // 45x195
//        else if (remHeight == 22)
//            remMaterial = materialFacade.getSpecificMaterial(28); // 45x220
//        else if (remHeight == 24.5)
//            remMaterial = materialFacade.getSpecificMaterial(29); // 45x245

        int remLength = carport.getLength();
        double priceRem = remMaterial.getPrice() * remLength / 100 * 2;

        OrderLine rems = new OrderLine(remMaterial, remLength, 2, priceRem);
        completeOrder.add(rems);


        // Spær
        // Sterner
        // Skruer og beslag
    }




}
