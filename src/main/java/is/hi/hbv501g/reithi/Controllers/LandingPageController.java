package is.hi.hbv501g.reithi.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LandingPageController  {

    @RequestMapping("/")
    public String LandingPageController() {

        return "landingPage.html";
    }

}
