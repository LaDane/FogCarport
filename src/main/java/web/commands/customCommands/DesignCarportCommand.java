package web.commands.customCommands;

import business.entities.User;
import business.helpers.SVGDrawing;
import web.commands.CommandUnprotectedPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DesignCarportCommand extends CommandUnprotectedPage {

    public DesignCarportCommand(String pageToShow) {
        super(pageToShow);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int hasFlatRoof = Integer.parseInt(request.getParameter("hasFlatRoof"));
        int hasShed = Integer.parseInt(request.getParameter("hasShed"));
        int newInquiryStarted = Integer.parseInt(request.getParameter("newInquiryStarted"));

        // 1 = true
        request.getSession().setAttribute("hasFlatRoof", hasFlatRoof);
        request.getSession().setAttribute("hasShed", hasShed);
        request.getSession().setAttribute("newInquiryStarted", newInquiryStarted);

        // Shed placement SVG
        request.getSession().setAttribute("shedPlacement1", SVGDrawing.getShedPlacementSVG(1));
        request.getSession().setAttribute("shedPlacement2", SVGDrawing.getShedPlacementSVG(2));
        request.getSession().setAttribute("shedPlacement3", SVGDrawing.getShedPlacementSVG(3));

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "loginsignup";
        }
        return pageToShow;
    }
}
