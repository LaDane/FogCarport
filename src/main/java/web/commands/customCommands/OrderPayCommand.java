package web.commands.customCommands;

import business.entities.views.OrderView;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderPayCommand extends CommandProtectedPage {
    public OrderPayCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderFacade orderFacade = new OrderFacade(database);

        OrderView orderView = (OrderView) request.getSession().getAttribute("orderSingle");
        orderView.setStatus("Betalt");
        request.getSession().setAttribute("orderSingle", orderView);

        orderFacade.updateOrderStatus(orderView.getOrderId(), "Betalt");

        request.setAttribute("confirmMsg","Betaling modtaget. Tak for handlen :)");

        return pageToShow;
    }
}
