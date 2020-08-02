package ddns.net.tracerandroidservice.util.payloads;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import java.io.Serializable;

public class KeySendRequest implements Serializable {

    private long id;

    private String password;

    @Email
    private String email;

    public KeySendRequest(){}

    public KeySendRequest(@JsonProperty("email") String email,
                          @JsonProperty("id") long id,
                          @JsonProperty("password") String password){
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
