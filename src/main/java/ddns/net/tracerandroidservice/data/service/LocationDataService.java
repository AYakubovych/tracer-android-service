package ddns.net.tracerandroidservice.data.service;



import ddns.net.tracerandroidservice.data.entities.LocationData;

import java.util.List;

public interface LocationDataService  {

    LocationData save(LocationData locationData);
    LocationData findOneById(long id);
    List<LocationData> findAllByTargetId(long id);
    List<LocationData> findAllByTargetIdAndDate(long id,String date);
    LocationData findOneByTargetIdAndDateAndTime(long id,String date,String time);
}
