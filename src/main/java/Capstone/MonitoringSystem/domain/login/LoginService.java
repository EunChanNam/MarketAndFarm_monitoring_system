package Capstone.MonitoringSystem.domain.login;

import Capstone.MonitoringSystem.domain.Manager.Manager;
import Capstone.MonitoringSystem.domain.Manager.managerrepository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final ManagerRepository mr;

    public Manager login(String loginId, String pw) {
        return mr.findByLoginId(loginId)
                .filter(m -> m.getPw().equals(pw))
                .orElse(null);
    }
}
