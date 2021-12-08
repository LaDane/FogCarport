package web.commands.customCommands;

import business.entities.views.OrderView;
import business.helpers.helper;
import business.services.OrderFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePriceTableCommand extends CommandProtectedPage {
    public UpdatePriceTableCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        OrderFacade orderFacade = new OrderFacade(database);
        OrderView orderView = (OrderView) request.getSession().getAttribute("orderSingle");
        OrderView newOrderView = orderFacade.getOrderViewByOrderId(orderView.getOrderId(), orderView.getUser());
        request.getSession().setAttribute("orderSingle", newOrderView);

        double orderPrice = (double) request.getSession().getAttribute("orderPrice");
        int priceReductionPercent = Integer.parseInt(request.getParameter("priceReductionPercent"));
        int priceIncreasePercent = Integer.parseInt(request.getParameter("priceIncreasePercent"));

        request.getSession().setAttribute("priceReductionPercent", priceReductionPercent);
        request.getSession().setAttribute("priceIncreasePercent", priceIncreasePercent);

        double purchasePrice = helper.round(orderPrice * ((double) priceReductionPercent / 100), 2);
        request.getSession().setAttribute("purchasePrice", purchasePrice);

        double suggestedPrice = helper.round(purchasePrice * (((double) priceIncreasePercent / 100) + 1), 2);
        request.getSession().setAttribute("suggestedPrice", suggestedPrice);

        double profit = suggestedPrice - purchasePrice;
        profit = helper.round(profit, 2);
        request.getSession().setAttribute("profit", profit);

        orderFacade.updatePricePercent(priceReductionPercent,priceIncreasePercent,orderView.getOrderId());

        return pageToShow;
    }

}
