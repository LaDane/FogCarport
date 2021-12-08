package web.commands.customCommands;

import business.carportCalc.CarportCalculator;
import business.carportCalc.FittingsCalculator;
import business.entities.Carport;
import business.entities.Order;
import business.entities.OrderLine;
import business.entities.User;
import business.entities.views.OrderView;
import business.exceptions.UserException;
import business.helpers.helper;
import business.services.MaterialFacade;
import business.services.OrderFacade;
import business.services.UserFacade;
import web.commands.CommandProtectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        MaterialFacade materialFacade = new MaterialFacade(database);
        CarportCalculator carportCalculator = new CarportCalculator(materialFacade);
        FittingsCalculator fittingsCalculator = new FittingsCalculator(materialFacade);

        User orderUser = null;
        try {orderUser = userFacade.getUserById(orderUserId);}
        catch (UserException e) {e.printStackTrace();}

        OrderView orderView = orderFacade.getOrderViewByOrderId(viewOrderId, orderUser);
        request.getSession().setAttribute("orderSingle", orderView);

        Carport carport = orderView.getCarport();
        List<OrderLine> orderLines = carportCalculator.calculateCarportMaterials(carport);

        List<OrderLine> orderLinesFittings = fittingsCalculator.calculateCarportMaterials(orderLines, carport);

        double orderPrice = carportCalculator.calculateCarportPrice(carport);
        double orderFittingsPrice = fittingsCalculator.calculateFittingsPrice(orderLines, carport);
        orderPrice += orderFittingsPrice;
        orderPrice = helper.round(orderPrice, 2);

        request.getSession().setAttribute("orderLines", orderLines);
        request.getSession().setAttribute("orderLinesFittings", orderLinesFittings);
        request.getSession().setAttribute("orderPrice", orderPrice);

        double purchasePrice = helper.round(orderPrice * 0.5, 2);
        request.getSession().setAttribute("priceReductionPercent", 50);
        request.getSession().setAttribute("purchasePrice", purchasePrice);

        double suggestedPrice = helper.round(purchasePrice * 1.7, 2);
        request.getSession().setAttribute("priceIncreasePercent", 70);
        request.getSession().setAttribute("suggestedPrice", suggestedPrice);

        // TODO: price reduction = 50%

        return pageToShow;
    }
}
