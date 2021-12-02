package web.commands.customCommands;

import business.entities.User;
import business.entities.views.OrderView;
import business.exceptions.UserException;
import business.services.OrderFacade;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderSingleCommand extends CommandProtectedPage {
    public OrderSingleCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int orderUserId = Integer.parseInt(request.getParameter("orderUserId"));
        int viewOrderId = Integer.parseInt(request.getParameter("viewOrderId"));

        UserFacade userFacade = new UserFacade(database);
        OrderFacade orderFacade = new OrderFacade(database);

        User orderUser = null;
        try {orderUser = userFacade.getUserById(orderUserId);}
        catch (UserException e) {e.printStackTrace();}

        OrderView orderView = orderFacade.getOrderViewByOrderId(viewOrderId, orderUser);
        request.getSession().setAttribute("orderSingle", orderView);

        return pageToShow;
    }
}
