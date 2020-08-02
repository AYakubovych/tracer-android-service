package ddns.net.tracerandroidservice.web;

import ddns.net.tracerandroidservice.data.entities.Target;
import ddns.net.tracerandroidservice.data.service.TargetService;
import ddns.net.tracerandroidservice.util.payloads.LogInRequest;
import ddns.net.tracerandroidservice.util.payloads.SignUpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private TargetService targetService;

    @PostMapping("/signin")
    public LogInRequest login(@Valid @RequestBody LogInRequest request){
        logger.info("Logging: " + request.getEmail());

        long id = 0;
        Target target = targetService.findOneByEmail(request.getEmail());

        //If exists and credentials check
        if(target != null ) {
           if(target.getPass().equals(request.getPassword())) {
               id = target.getId();
               logger.info("All ok. Target id: " + id);
           }
        }else{
            logger.error("No such user or incorrect password. Returning 0");
        }

        LogInRequest logInRequest = new LogInRequest();
        logInRequest.setId(id);

        return logInRequest;
    }

    @PostMapping("/signup")
    public SignUpRequest createProfile(@Valid @RequestBody SignUpRequest request){

        Target target = new Target();
        logger.info("Registration for: " + request.getEmail());

        if(request.getEmail().length() == 0){
            logger.error("Email is empty");

            SignUpRequest response = new SignUpRequest();
            response.setId(0);

            return response;

        }else if(targetService.findOneByEmail(request.getEmail()) != null){
            logger.error("Email already in use: " + request.getEmail());

            SignUpRequest response = new SignUpRequest();
            response.setId(0);

            return response;

        }else{
            target.setEmail(request.getEmail());
            target.setName(request.getFirstName());
            target.setSurname(request.getLastName());
            target.setPhone(request.getPhone());
            target.setPass(request.getPassword());

            target = targetService.save(target);
            logger.info("Target saved, id : " + target.getId());
        }
        return new SignUpRequest(target);
    }

    @Autowired
    public void setTargetService(TargetService targetService) {
        this.targetService = targetService;
    }
}
