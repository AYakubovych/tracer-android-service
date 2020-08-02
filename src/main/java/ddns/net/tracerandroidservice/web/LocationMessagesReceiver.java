package ddns.net.tracerandroidservice.web;

import ddns.net.tracerandroidservice.config.Constants;
import ddns.net.tracerandroidservice.data.entities.LocationData;
import ddns.net.tracerandroidservice.data.service.LocationDataService;
import ddns.net.tracerandroidservice.util.payloads.LocationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationMessagesReceiver {

    private static Logger logger = LoggerFactory.getLogger(LocationMessagesReceiver.class);

    private LocationDataService locationDataService;

    @RabbitListener(queues = Constants.queueName)
    public void receiveMessage(LocationMessage locationMessage) {

        System.out.println(locationMessage);

        LocationData locationData = new LocationData(locationMessage);

        locationData = locationDataService.save(locationData);
        logger.info("Location saved,id :" + locationData.getId());
    }

    @Autowired
    public void setLocationDataService(LocationDataService locationDataService) {
        this.locationDataService = locationDataService;
    }
}
