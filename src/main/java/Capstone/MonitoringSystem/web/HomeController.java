package Capstone.MonitoringSystem.web;

import Capstone.MonitoringSystem.LoginConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@SessionAttribute(name = LoginConst.LOGIN_MANAGER, required = false) Long managerId) {

        if (managerId == null) {
            return "redirect:/login";
        }
        return "main";
    }
}
