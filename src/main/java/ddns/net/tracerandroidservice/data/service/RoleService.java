package ddns.net.tracerandroidservice.data.service;

import ddns.net.tracerandroidservice.data.entities.Role;
import ddns.net.tracerandroidservice.data.entities.RoleName;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {
    Optional<Role> findByRole(RoleName role);
}
