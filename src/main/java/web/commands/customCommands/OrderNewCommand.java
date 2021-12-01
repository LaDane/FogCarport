package web.commands.customCommands;

import business.entities.*;
import business.services.MaterialFacade;
import com.sun.org.apache.xpath.internal.operations.Or;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderNewCommand extends CommandProtectedPage {

    public OrderNewCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {


        int carportWidth = Integer.parseInt(request.getParameter("carportWidth"));
        int carportLength = Integer.parseInt(request.getParameter("carportLength"));
        int roofMaterial = Integer.parseInt(request.getParameter("roofMaterial"));
        int roofSlope = -1;
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

        int hasShed = (int) request.getSession().getAttribute("hasShed");
        if (hasShed == 1) {
            shedWidth = Integer.parseInt(request.getParameter("shedWidth"));
            shedLength = Integer.parseInt(request.getParameter("shedLength"));
            shedPlacement = request.getParameter("shedPlacement");
            shedCladding = Integer.parseInt(request.getParameter("shedCladding"));

            Cladding cladding = (Cladding) materialFacade.getSpecificMaterial(shedCladding);

            shed = new Shed(cladding, shedPlacement, shedWidth, shedLength);
        }

        Carport carport = new Carport(carportWidth, carportLength, shed);
        Order order = new Order("Forespørgsel", user, carport);

        System.out.println(order.getStatus());
        System.out.println(order.getCarport().getLength());

        return pageToShow;
    }
}
