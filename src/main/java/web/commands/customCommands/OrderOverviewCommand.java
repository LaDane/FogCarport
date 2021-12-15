package web.commands.customCommands;

import business.entities.User;
import business.entities.views.OrderView;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderOverviewCommand extends CommandProtectedPage {
    public OrderOverviewCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderFacade orderFacade = new OrderFacade(database);

        User user = (User) request.getSession().getAttribute("user");

        if (user.getRole().equals("employee")) {
            List<OrderView> allOrderViews = orderFacade.getAllOrderViews(null);

            // Removes an order if the order is ended
            allOrderViews.removeIf(orders -> orders.getStatus().equals("Afsluttet"));

            request.getSession().setAttribute("allOrderViews", allOrderViews);
        }
        else if (user.getRole().equals("customer")) {
            List<OrderView> allOrderViews = orderFacade.getAllOrderViews(user);
            request.getSession().setAttribute("allOrderViews", allOrderViews);
        }

        return pageToShow;
    }
}
