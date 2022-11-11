package is.hi.hbv501g.reithi.Controllers;


import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user) {
        return "landingPage";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(@ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "redirect:/signup";
        }
        User exists = userService.findByUserName(user.getUserName());
        if (exists == null) {
            exists = userService.save(user);
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user) {
        return "landingPage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(@ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "landingPage";
        }
        User exists = userService.login(user);
        if (exists != null) {
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/loggedin";
        }
        // TODO: case when user does not exist
        return "redirect:/";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            model.addAttribute("LoggedInUser", sessionUser);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutGET(HttpSession session, Model model) {
        session.setAttribute("LoggedInUser", null);
        model.addAttribute("LoggedInUser", null);

        return "redirect:/";
    }

}