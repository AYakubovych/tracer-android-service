package ddns.net.tracerandroidservice.util.payloads;

public class KeySendRedirectionRequest {

    private String targetEmail;

    private String email;

    private String key;

    public KeySendRedirectionRequest(){}

    public KeySendRedirectionRequest(String targetEmail, String email, String key) {
        this.targetEmail = targetEmail;
        this.email = email;
        this.key = key;
    }

    public String getTargetEmail() {
        return targetEmail;
    }

    public void setTargetEmail(String targetEmail) {
        this.targetEmail = targetEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
