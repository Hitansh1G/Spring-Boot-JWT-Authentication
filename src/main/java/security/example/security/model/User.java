package security.example.security.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name = "Users")
public class User implements UserDetails {

    @PrePersist
    protected void onCreate(){
        this.created_At= new Date(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_At= new Date(System.currentTimeMillis());
    }

    @Id
    private String user_id;
    private String user_name;
    private String email;
    private String password;
    private String mobile_number;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="User_id"),
            inverseJoinColumns = @JoinColumn(name = "Role_id")
    )
    private Set<Role> roles = new HashSet<>();

    private Date created_At;
    private Date updated_At;

     public User(String mobile_number, String user_name, String email, String password, Set<Role>roles){
         this.user_id = email;
         this.mobile_number = mobile_number;
         this.user_name = user_name;
         this.email = email;
         this.password = password;
         this.roles = roles;
     }

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority>authorities = new ArrayList<>();
        roles.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return email ;
    }

    @Override
    public String getPassword() {
        return  password;
    }


    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
