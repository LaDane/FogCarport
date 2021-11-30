package web.commands;

import business.entities.User;
import business.persistence.Database;
import business.services.UserFacade;
import business.exceptions.UserException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand extends CommandUnprotectedPage {
    private UserFacade userFacade;

    public RegisterCommand(String pageToShow) {
        super(pageToShow);
        userFacade = new UserFacade(database);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws UserException {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String phoneNumber = request.getParameter("phoneNumber");

        // We have a hidden input field that will indicate if we have pressed the submit button and started our inquiry on the index page.
        String newInquiryStarted = String.valueOf(request.getSession().getAttribute("newInquiryStarted"));

        if (password1.equals(password2)) {
            if (!userFacade.emailExist(email)) {
                User user = userFacade.createUser(email, password1, name, address, zip, city, phoneNumber);
                HttpSession session = request.getSession();

                session.setAttribute("email", email);
                session.setAttribute("user", user);
                session.setAttribute("role", user.getRole());
                session.setAttribute("name", name);

                // If we have an active inquiry, then we want to continue with our customization
                if (newInquiryStarted.equals("1")) {

                    // Remove the inquiry check mark.
                    request.getSession().removeAttribute("newInquiryStarted");
                    return "designcarport";
                } else {
                    return "index";
                }

            } else {
                request.setAttribute("errorSignup", "email already exists");
                return "loginsignup";
            }
        } else {
            request.setAttribute("errorSignup", "the two passwords did not match");
            return "loginsignup";
        }
    }
}
