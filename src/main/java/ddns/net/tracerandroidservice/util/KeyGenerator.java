package ddns.net.tracerandroidservice.util;

import ddns.net.tracerandroidservice.data.service.BindingKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KeyGenerator {

    private BindingKeyService bindingKeyService;

    private final int KEY_LENGTH = 7;

    /**
     * Checks the generated key for existence, if exists - generates a new one,
     * if not returns it.
     * */
    public String getKey(){

        String key = "";
        do{
            key = generateKey();
        }
        while(bindingKeyService.findOneByKey(key) != null);

        return key;
    }

    /**
     * Generate a random string in uppercase with the length depending on the final KEY_LENGTH value.
     * */
    private String generateKey(){

        Random random = new Random();

        StringBuilder key = new StringBuilder();
        for(int i = KEY_LENGTH; i > 0; i--){
            char c = (char) (random.nextInt(26) + 'A');
            key.append(c);
        }
        return key.toString();
    }

    @Autowired
    public void setBindingKeyService(BindingKeyService bindingKeyService) {
        this.bindingKeyService = bindingKeyService;
    }
}
