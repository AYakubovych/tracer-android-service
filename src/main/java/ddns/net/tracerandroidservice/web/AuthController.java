package ddns.net.tracerandroidservice.web;

import ddns.net.tracerandroidservice.data.entities.Target;
import ddns.net.tracerandroidservice.data.service.TargetService;
import ddns.net.tracerandroidservice.util.payloads.TargetCredentialPayload;
import ddns.net.tracerandroidservice.util.payloads.TargetDataPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/android")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private TargetService targetService;

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody TargetCredentialPayload payload){

        Target target = targetService.findOneByEmail(payload.getEmail());

        if(target == null){
            throw new RuntimeException("No such user");
        }else if(!target.getPass().equals(payload.getPassword())){
            throw new RuntimeException("Password incorrect");
        }
        return ResponseEntity.ok(target.getId());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createProfile(@Valid @RequestBody TargetDataPayload payload){

        Target target = new Target();

        if(targetService.findOneByEmail(payload.getEmail()) != null){
            throw new RuntimeException("Email already in use");
        }else{
            target.setEmail(payload.getEmail());
            target.setName(payload.getFirstName());
            target.setSurname(payload.getLastName());
            target.setPhone(payload.getPhone());
            target.setPass(payload.getPassword());

            target = targetService.save(target);
            logger.info("Target saved, id : " + target.getId());
        }
        return ResponseEntity.ok(target.getId());
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }
}
