package ddns.net.tracerandroidservice.data.repository;

import ddns.net.tracerandroidservice.data.entities.BindingKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BindingKeyRepository extends CrudRepository<BindingKey, Long> {

    BindingKey findOneById(long id);
    BindingKey findOneByKey(String key);
}
