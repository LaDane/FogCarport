package web;

import business.entities.views.OrderView;
import business.exceptions.UserException;
import business.persistence.Database;
import business.services.LogicFacade;
import business.services.MaterialFacade;
import business.services.OrderFacade;
import com.sun.org.apache.xpath.internal.operations.Or;
import web.commands.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "FrontController", urlPatterns = {"/fc/*"})
public class FrontController extends HttpServlet
{
    private final static String USER = "root";
    private final static String PASSWORD = getSecret();
    private final static String URL = "jdbc:mysql://localhost:3306/fog?serverTimezone=CET";

    public static Database database;


    public void init() throws ServletException
    {
        // Initialize database connection
        if (database == null)
        {
            try
            {
                database = new Database(USER, PASSWORD, URL);
            }
            catch (ClassNotFoundException ex)
            {
                Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            }
        }

        // Initialize whatever global datastructures needed here:
        LogicFacade logicFacade = new LogicFacade(database);
        getServletContext().setAttribute("standardLengths", logicFacade.getStandardLengths());
        getServletContext().setAttribute("carportLengths", logicFacade.getCarportLengths());
        getServletContext().setAttribute("carportWidths", logicFacade.getCarportWidths());

        MaterialFacade materialFacade = new MaterialFacade(database);
        getServletContext().setAttribute("materials", materialFacade.getAllMaterials());
        getServletContext().setAttribute("woods", materialFacade.getAllWoods());
        getServletContext().setAttribute("fittings", materialFacade.getAllFittings());
        getServletContext().setAttribute("screws", materialFacade.getAllScrews());
        getServletContext().setAttribute("roofFlats", materialFacade.getAllRoofFlats());
        getServletContext().setAttribute("roofRaiseds", materialFacade.getAllRoofRaiseds());
        getServletContext().setAttribute("claddings", materialFacade.getAllCladdings());

//        OrderFacade orderFacade = new OrderFacade(database);

    }

    protected void processRequest(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException

    {
        try
        {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Command action = Command.fromPath(request, database);

            if (action instanceof CommandUnknown) {
                response.sendError(404);
                return;
            }

            String view = action.execute(request, response);

            if (view.startsWith(Command.REDIRECT_INDICATOR)) {
                String page = view.substring(Command.REDIRECT_INDICATOR.length());
                response.sendRedirect(page);
                return;
            }

            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        }
        catch (UnsupportedEncodingException | UserException ex)
        {
            request.setAttribute("problem", ex.getMessage());
            Logger.getLogger("web").log(Level.SEVERE, ex.getMessage(), ex);
            request.getRequestDispatcher("/errorpage.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo()
    {
        return "FrontController for application";
    }

    // Must have a file names password.secret located in the C drive to be able to connect to a SQL database
    private static String getSecret() {
        String secretWord = "file not found";

        try {
            Scanner scanner = new Scanner(new File("C:\\password.secret"));
            secretWord = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException ignored) {}
        return secretWord;
    }
}
