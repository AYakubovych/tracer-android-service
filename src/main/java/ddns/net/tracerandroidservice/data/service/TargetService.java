package ddns.net.tracerandroidservice.data.service;


import ddns.net.tracerandroidservice.data.entities.Target;

public interface TargetService {

    Target save(Target child);
    Target findOneByName(String name);
    Target findOneById(long id);
    Target findOneByEmail(String email);

}
