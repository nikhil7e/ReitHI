package is.hi.hbv501g.reithi.Controllers.Rest;

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
import java.util.Objects;

/**
 * This controller handles HTTP requests for user functionality, such as logging in, signing up and logging out.
 */
@Controller
public class UserRESTController {

    private UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Saves a new user in the database
     *
     * @param user The user to be saved in the database
     * @return The landing page template
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public User signupPOST(@ModelAttribute("user") User user) {
        User exists = userService.findByUserName(user.getUserName());

        if (exists == null) {
            return userService.save(user);
        }

        return null;
    }

    /**
     * Saves a new user in the database
     *
     * @return The landing page template
     */
    @RequestMapping(value = "/finduser", method = RequestMethod.POST)
    public User getUserPOST(@ModelAttribute("username") String username) {
        return userService.findByUserName(username);
    }

    /**
     * Logs a user in if the user account exists
     *
     * @param user The user to be logged in
     * @return If the user account exists, send a GET request to /loggedin, else return the landing page template
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User loginPOST(@ModelAttribute("user") User user) {
        return userService.login(user);
    }

}