package ddns.net.tracerandroidservice.data.service;

import ddns.net.tracerandroidservice.data.entities.BindingKey;

public interface BindingKeyService {

    BindingKey save(BindingKey bindingKey);
    BindingKey findOneById(long id);
    BindingKey findOneByKey(String key);
    void delete(BindingKey bindingKey);
}
