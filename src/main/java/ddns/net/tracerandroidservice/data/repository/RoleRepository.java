package ddns.net.tracerandroidservice.data.repository;

import ddns.net.tracerandroidservice.data.entities.Role;
import ddns.net.tracerandroidservice.data.entities.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByRole(RoleName Role);
}
