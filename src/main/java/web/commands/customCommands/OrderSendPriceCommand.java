package web.commands.customCommands;

import business.entities.views.OrderView;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderSendPriceCommand extends CommandProtectedPage {

    public OrderSendPriceCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderFacade orderFacade = new OrderFacade(database);

        OrderView orderView = (OrderView) request.getSession().getAttribute("orderSingle");
        OrderView newOrderView = orderFacade.getOrderViewByOrderId(orderView.getOrderId(), orderView.getUser());
        request.getSession().setAttribute("orderSingle", newOrderView);

        orderFacade.updateOrderStatus(orderView.getOrderId(), "Tilbud sendt");

        request.setAttribute("confirmMsg","Tilbud er sendt til: " + orderView.getUser().getName());

        return pageToShow;
    }
}