package ddns.net.tracerandroidservice.data.service;


import ddns.net.tracerandroidservice.data.entities.LocationData;
import ddns.net.tracerandroidservice.data.repository.LocationDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationDataServiceImpl implements LocationDataService {

    private static Logger logger = LoggerFactory.getLogger(LocationDataServiceImpl.class);

    private LocationDataRepository locationDataRepository;

    @Transactional
    @Override
    public LocationData save(LocationData locationData){
        return locationDataRepository.save(locationData);
    }

    @Transactional(readOnly = true)
    @Override
    public LocationData findOneById(long id) {
        return locationDataRepository.findOneById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LocationData> findAllByTargetId(long id) {
        return locationDataRepository.findAllByTargetId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LocationData> findAllByTargetIdAndDate(long id, String date) {
        return locationDataRepository.findAllByTargetIdAndDate(id,date);
    }

    @Transactional(readOnly = true)
    @Override
    public LocationData findOneByTargetIdAndDateAndTime(long id, String date, String time) {
        return locationDataRepository.findOneByTargetIdAndDateAndTime(id,date,time);
    }

    @Autowired
    public void setLocationDataRepository(LocationDataRepository locationDataRepository) {
        this.locationDataRepository = locationDataRepository;
    }
}
