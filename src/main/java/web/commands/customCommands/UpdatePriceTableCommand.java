package web.commands.customCommands;

import business.helpers.helper;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePriceTableCommand extends CommandProtectedPage {
    public UpdatePriceTableCommand(String pageToShow, String role) {
        super(pageToShow, role);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        double orderPrice = (double) request.getSession().getAttribute("orderPrice");
        int priceReductionPercent = Integer.parseInt(request.getParameter("priceReductionPercent"));
        int priceIncreasePercent = Integer.parseInt(request.getParameter("priceIncreasePercent"));

        request.getSession().setAttribute("priceReductionPercent", priceReductionPercent);
        request.getSession().setAttribute("priceIncreasePercent", priceIncreasePercent);

        double purchasePrice = helper.round(orderPrice * ((double) priceReductionPercent / 100), 2);
        request.getSession().setAttribute("purchasePrice", purchasePrice);

        double suggestedPrice = helper.round(purchasePrice * (((double) priceIncreasePercent / 100) + 1), 2);
        request.getSession().setAttribute("suggestedPrice", suggestedPrice);


        return pageToShow;
    }

}
