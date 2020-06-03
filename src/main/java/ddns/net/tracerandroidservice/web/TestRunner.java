package ddns.net.tracerandroidservice.web;

import ddns.net.tracerandroidservice.config.Constants;
import ddns.net.tracerandroidservice.util.LocationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Only for testing purpose.
 * Class create a messages for RabbitMQ.
 */
@Component
public class TestRunner  {

    private static Logger logger = LoggerFactory.getLogger(TestRunner.class);

    private final RabbitTemplate rabbitTemplate;

    public TestRunner(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        final LocationMessage message = new LocationMessage(1, 50.554, 22.4);
        logger.info("Sending message...");
        rabbitTemplate.convertAndSend(Constants.topicExchangeName, "",message);
    }
}
