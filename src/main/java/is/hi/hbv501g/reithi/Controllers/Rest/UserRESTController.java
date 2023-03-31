package is.hi.hbv501g.reithi.Controllers.Rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import is.hi.hbv501g.reithi.Persistence.Entities.Course;
import is.hi.hbv501g.reithi.Persistence.Entities.User;
import is.hi.hbv501g.reithi.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

/**
 * This controller handles HTTP requests for user functionality, such as logging in, signing up and logging out.
 */
@RestController
public class UserRESTController {

    private UserService userService;

    @Autowired
    public UserRESTController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Saves a new user in the database
     *
     * @return The landing page template
     */
    @RequestMapping(value = "/api/signup", method = RequestMethod.POST)
    public User signupPOST(@RequestBody Map<String, String> json) throws JsonProcessingException  {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);

        User exists = userService.findByUserName(user.getUserName());

        if (exists == null) {
            String token = objectMapper.readValue(json.get("deviceToken"), String.class);
            user.setDeviceToken(token);
            return userService.save(user);
        }

        return null;
    }

    /**
     * Saves a new user in the database
     *
     * @return The landing page template
     */
    @RequestMapping(value = "/api/finduser", method = RequestMethod.POST)
    public User getUserPOST(@RequestBody Map<String, String> payload) {
        return userService.findByUserName(payload.get("username"));
    }

    @RequestMapping(value = "/api/getuserbyid", method = RequestMethod.GET)
    public User genericGET(@RequestParam("id") int id ) {
        return userService.findByID(id);
    }


    /**
     * Logs a user in if the user account exists
     *
     * @return If the user account exists, send a GET request to /loggedin, else return the landing page template
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    public User loginPOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        return userService.login(user);
    }

    @RequestMapping(value = "/api/updatetoken", method = RequestMethod.POST)
    public User updateDeviceTokenPOST(@RequestBody Map<String, String> json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json.get("user"), User.class);
        String token = objectMapper.readValue(json.get("deviceToken"), String.class);
        user.setDeviceToken(token);
        return userService.save(user);
    }



}