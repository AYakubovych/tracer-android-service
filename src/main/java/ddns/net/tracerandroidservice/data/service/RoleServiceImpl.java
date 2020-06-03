package ddns.net.tracerandroidservice.data.service;


import ddns.net.tracerandroidservice.data.entities.Role;
import ddns.net.tracerandroidservice.data.entities.RoleName;
import ddns.net.tracerandroidservice.data.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Role> findByRole(RoleName role) {
        return roleRepository.findByRole(role);
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
