package Capstone.MonitoringSystem.web.login;

import Capstone.MonitoringSystem.LoginConst;
import Capstone.MonitoringSystem.domain.Manager.Manager;
import Capstone.MonitoringSystem.domain.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm loginForm,
                            @ModelAttribute("redirectURI") String redirectURI) {

        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(name = "redirectURI",required = false) String redirectURI) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        Manager loginManager = loginService.login(loginForm.getLoginId(), loginForm.getPw());

        if (loginManager == null) {
            bindingResult.reject("loginFail", "아이디 혹은 비밀번호가 틀렸습니다.");
            return "login";
        }

        HttpSession session = request.getSession(true);
        session.setAttribute(LoginConst.LOGIN_MANAGER, loginManager.getId());

        if (StringUtils.hasText(redirectURI)) {
            return "redirect:" + redirectURI;
        }
        return "redirect:/";
    }
}
