package web.commands;

import business.entities.User;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand extends CommandUnprotectedPage {
    private UserFacade userFacade;

    public LoginCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {

        String email = request.getParameter("loginemail");
        String password = request.getParameter("password");

        // We have a hidden input field that will indicate if we have pressed the submit button and started our inquiry on the index page.
        String newInquiryStarted = String.valueOf(request.getSession().getAttribute("newInquiryStarted"));


        // TODO : FINISH LOGIC BELOW


        try {
            User user = userFacade.login(email, password);

            HttpSession session = request.getSession();

            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", email);

            // If we have an active inquiry, then we want to continue with our customization
            if (newInquiryStarted.equals("1")) {

                // Remove the inquiry check mark.
                request.getSession().removeAttribute("newInquiryStarted");
                return "designcarport";
            }
        } catch (UserException ex) {
            request.setAttribute("errorLogin", "Wrong username or password!");
            return "loginsignup";
        }

        // If we just logged in without having an active inquiry ongoing, we will just return to index site after successful login.
        return "index";
    }
}
