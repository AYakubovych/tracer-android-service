package ddns.net.tracerandroidservice.util.payloads;

import com.sun.istack.NotNull;

public class TargetCredentialPayload {

    @NotNull
    private String email;
    @NotNull
    private String password;

    public TargetCredentialPayload(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
