package Capstone.MonitoringSystem;

import Capstone.MonitoringSystem.web.login.logininterceptor.loginInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginInterceptor())
                .order(1)
                .addPathPatterns("/stocks/**", "/releases/**", "storages/**");
    }
}
