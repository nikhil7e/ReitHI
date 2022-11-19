package is.hi.hbv501g.reithi.Controllers;


import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * This controller handles HTTP requests for user functionality, such as logging in, signing up and logging out.
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public String signupGET(User user) {
//        return "landingPage";
//    }

    /**
     * Saves a new user in the database
     *
     * @param user    The user to be saved in the database
     * @param result  The requests binding result
     * @param model   The applications model
     * @param session The applications session
     * @return The landing page template
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(@ModelAttribute("user") User user, BindingResult result, Model model,
                             HttpSession session, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        if (result.hasErrors()) {
            return (String) session.getAttribute("currentPage");
        }
        User exists = userService.findByUserName(user.getUserName());
        if (exists == null) {
            exists = userService.save(user);
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
        }

        return (String) session.getAttribute("currentPage");
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String loginGET(User user) {
//        return "landingPage";
//    }

    /**
     * Logs a user in if the user account exists
     *
     * @param user    The user to be logged in
     * @param result  The requests binding result
     * @param model   The applications model
     * @param session The applications session
     * @return If the user account exists, send a GET request to /loggedin, else return the landing page template
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        if (result.hasErrors()) {
            return (String) session.getAttribute("currentPage");
        }

        User exists = userService.login(user);
        if (exists != null) {
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
        }

        // TODO: Notify user if account does not exist
        return (String) session.getAttribute("currentPage");
    }

//    /**
//     *
//     *
//     * @param session
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
//    public String loggedinGET(HttpSession session, Model model) {
//        User sessionUser = (User) session.getAttribute("LoggedInUser");
//        if (sessionUser != null) {
//            model.addAttribute("LoggedInUser", sessionUser);
//        }
//        return "redirect:/";
//    }

    /**
     * Logs a user out
     *
     * @param session The applications session
     * @param model   The applications model
     * @return The landing page template
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGET(HttpSession session, Model model, @RequestHeader(value = HttpHeaders.REFERER, required = false) final String referrer) {
        session.setAttribute("LoggedInUser", null);
        model.addAttribute("LoggedInUser", null);

        return (String) session.getAttribute("currentPage");
    }

}