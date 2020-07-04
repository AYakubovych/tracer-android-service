package ddns.net.tracerandroidservice.web;

import ddns.net.tracerandroidservice.data.entities.BindingKey;
import ddns.net.tracerandroidservice.data.entities.Target;
import ddns.net.tracerandroidservice.data.service.BindingKeyService;
import ddns.net.tracerandroidservice.data.service.TargetService;
import ddns.net.tracerandroidservice.util.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/key")
public class KeyGenController {

    private Logger logger = LoggerFactory.getLogger(KeyGenController.class);

    private KeyGenerator keyGenerator;
    private TargetService targetService;
    private BindingKeyService bindingKeyService;

    /**
     * Controller take target id to generate new BindingKey
     */
    @GetMapping("/generate/{id}")
    public ResponseEntity<?> generateKey(@PathVariable long id){

        logger.info("Key generation for target: " + id);

        BindingKey bindingKey = new BindingKey();
        Target target = targetService.findOneById(id);

        //if no such target
        if(target == null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
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

        logger.info("Creating response for target: " + target.getId() + " , with key: " + bindingKey.getKey());
        return ResponseEntity.ok(bindingKey.getKey());
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
