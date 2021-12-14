package web.commands.customCommands;

import business.entities.*;
import business.entities.materials.Cladding;
import business.entities.materials.Material;
import business.entities.views.OrderView;
import business.exceptions.OrderException;
import business.services.MaterialFacade;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderNewCommand extends CommandProtectedPage {

    public OrderNewCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int roofMaterial = Integer.parseInt(request.getParameter("roofMaterial"));
        int roofSlope = 0;
        int shedWidth = -1;
        int shedLength = -1;
        int shedCladding = -1;
        String shedPlacement = null;

        // Facade
        MaterialFacade materialFacade = new MaterialFacade(database);

        // Entities
        User user = (User) request.getSession().getAttribute("user");
        Shed shed = null;

        int hasFlatRoof = (int) request.getSession().getAttribute("hasFlatRoof");
        if (hasFlatRoof == 0) {
            roofSlope = Integer.parseInt(request.getParameter("roofSlope"));
        }
        Material rMaterial = materialFacade.getSpecificMaterial(roofMaterial);
        Roof roof = new Roof(rMaterial, roofSlope);

        int hasShed = (int) request.getSession().getAttribute("hasShed");
        if (hasShed == 1) {
            shedPlacement = request.getParameter("shedPlacement");

            //shedCladding = Integer.parseInt(request.getParameter("shedCladding"));
            shedCladding = 26;

            Cladding cladding = (Cladding) materialFacade.getSpecificMaterial(shedCladding);


            shedLength = (carportLength / 3 - 30 + 14);  // minus poleoffset og plusset med poledimension

            if(shedPlacement.equals("N")){
                shedWidth = (carportWidth - 60 + 10);  // minus poleoffset og plusset med poledimension
            }else {
                shedWidth = (carportWidth / 2 - 30 + 10);  // minus poleoffset og plusset med poledimension
            }

            shed = new Shed(cladding, shedPlacement, shedWidth, shedLength);
        }

        // TODO: If we manage to implement raised roofs - change 250 height to variable

        Carport carport = new Carport(carportWidth, carportLength, 250, shed, roof);
        Order order = new Order("Forespørgsel", user, carport);

        OrderFacade orderFacade = new OrderFacade(database);
        int orderId = -1;
        try {
            orderId = orderFacade.createOrderEntry(order);
        } catch (OrderException e) {
            e.printStackTrace();
        }

        List<OrderView> allOrderViews = orderFacade.getAllOrderViews(user);
        request.getSession().setAttribute("allOrderViews", allOrderViews);
        request.setAttribute("inquiryMsg", "Din forespørgsel er modtaget. " +
                "Du kan forvente at høre tilbage fra os indenfor 24 timer.");

        return pageToShow;
    }
}
