package ddns.net.tracerandroidservice.util.payloads;

import ddns.net.tracerandroidservice.data.entities.Target;

public class SignUpRequest {

    private long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    public SignUpRequest() {
    }

    public SignUpRequest(Target target){
        this.email = target.getEmail();
        this.firstName = target.getName();
        this.id = target.getId();
        this.lastName = target.getSurname();
        this.phone = target.getPhone();
        this.password = target.getPass();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

