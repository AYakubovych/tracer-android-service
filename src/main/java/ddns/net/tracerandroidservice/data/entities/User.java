package ddns.net.tracerandroidservice.data.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.FIND_ALL",
        query= "select u from User u")
@SqlResultSetMapping(
        name = "userResult",
        entities = @EntityResult(entityClass = User.class)
)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "username")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name ="email")
    private String email;

    @JsonIgnore
    @Column(name = "pass")
    private String pass;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "user_target",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "target_id")})
    private List<Target> targets = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(){
        this.enabled = true;
    }

    public User(String name, String last_name, String email, String pass) {
        this.name = name;
        this.last_name = last_name;
        this.email = email;
        this.pass = pass;
        this.enabled = true;
    }

    public void addTarget(Target child){
        targets.add(child);
    }

    public List<Target> getTargets() {
        return targets;
    }

    public void setTargets(List<Target> targets) {
        this.targets = targets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnabled(){
        return enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return email;
    }
}
