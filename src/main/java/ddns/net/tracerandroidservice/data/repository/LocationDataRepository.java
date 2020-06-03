package ddns.net.tracerandroidservice.data.repository;


import ddns.net.tracerandroidservice.data.entities.LocationData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDataRepository extends CrudRepository<LocationData, Long> {

    LocationData findOneById(long id);
    List<LocationData> findAllByTargetId(long id);
    List<LocationData> findAllByTargetIdAndDate(long id, String date);
    LocationData findOneByTargetIdAndDateAndTime(long id,String date,String time);

}
