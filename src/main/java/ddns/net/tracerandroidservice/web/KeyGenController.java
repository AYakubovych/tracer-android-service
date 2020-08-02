package ddns.net.tracerandroidservice.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import ddns.net.tracerandroidservice.data.entities.BindingKey;
import ddns.net.tracerandroidservice.data.entities.Target;
import ddns.net.tracerandroidservice.data.service.BindingKeyService;
import ddns.net.tracerandroidservice.data.service.TargetService;
import ddns.net.tracerandroidservice.util.KeyGenerator;
import ddns.net.tracerandroidservice.util.payloads.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/key")
public class KeyGenController {

    private Logger logger = LoggerFactory.getLogger(KeyGenController.class);

    private KeyGenerator keyGenerator;
    private TargetService targetService;
    private BindingKeyService bindingKeyService;

    @Value("${mailing.service.url}")
    private String MAILING_URL;

    private final String KEY_SEND_ROUTING = "key/send";

    /**
     * Controller take target id to generate new BindingKey
     */
    @GetMapping(path = "/generate/{id}", produces = "application/json")
    public KeyRequest generateKey(@PathVariable long id){

        logger.info("Key generation for target: " + id);

        BindingKey bindingKey = new BindingKey();
        Target target = targetService.findOneById(id);

        //if no such target
        if(target == null){
            return new KeyRequest("No such user");
        }

        BindingKey odlKey = target.getBindingKey();

        bindingKey.setKey(keyGenerator.getKey());
        bindingKey = bindingKeyService.save(bindingKey);
        logger.info("BK id: " + bindingKey.getId());

        target.setBindingKey(bindingKey);
        targetService.save(target);

        //deleting old key if exist
        if(odlKey != null){
            logger.info("deleting old key");
            bindingKeyService.delete(odlKey);
        }
        //Sending new key on email
        sendKey(new KeySendRequest(
                target.getEmail(),
                target.getId(),
                target.getBindingKey().getKey()
        ));

        logger.info("Creating response for target: " + target.getId() + " , with key: " + bindingKey.getKey());
        return new KeyRequest(bindingKey.getKey());
    }

    @PostMapping(path = "/send", produces = "application/json")
    public Boolean sendKey(@Valid @RequestBody KeySendRequest request){
        //Auth
        logger.info("SendKey request for: " + request.getId());
        Target target = targetService.findOneById(request.getId());
        if(!request.getPassword().equals(target.getPass())){
            return false;
        }
        //Creating redirection body
        KeySendRedirectionRequest redirectionRequest = new KeySendRedirectionRequest(
               target.getEmail(),
                request.getEmail(),
                target.getBindingKey().getKey()
        );

        return redirection(redirectionRequest);
    }

    /**Redirection to mailing service
    * return isSended status
    */
     private boolean redirection(KeySendRedirectionRequest redirectionRequest){

        RestTemplate restTemplate = new RestTemplate();

        try{

                ApiResponse mailingServiceResponse = restTemplate.postForObject(
                    MAILING_URL + KEY_SEND_ROUTING,
                    redirectionRequest,
                    ApiResponse.class
            );
            //If request is successful
            if (mailingServiceResponse.getSuccess()) {
                logger.info("Mail sended");
                return true;
            }

        }catch (Exception e ){
            logger.error("Mail not sended");
            e.printStackTrace();
        }

        return false;
    }

    @Autowired
    public void setBindingKeyService(BindingKeyService bindingKeyService) {
        this.bindingKeyService = bindingKeyService;
    }
    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }
    @Autowired
    public void setKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

}
