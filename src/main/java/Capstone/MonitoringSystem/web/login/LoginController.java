package Capstone.MonitoringSystem.web.login;

import Capstone.MonitoringSystem.LoginConst;
import Capstone.MonitoringSystem.domain.Manager.Manager;
import Capstone.MonitoringSystem.domain.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm) {

        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "loginForm";
        }

        Manager loginManager = loginService.login(loginForm.getLoginId(), loginForm.getPw());

        if (loginManager == null) {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 틀렸습니다.");
            return "loginForm";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(LoginConst.LOGIN_MANAGER, loginManager.getId());

        return "redirect:/";
    }
}
