package ddns.net.tracerandroidservice.data.repository;


import ddns.net.tracerandroidservice.data.entities.Target;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends CrudRepository<Target,Integer> {

    Target findOneByName(String name);
    Target findOneById(long id);
    Target findOneByEmail(String email);

}
