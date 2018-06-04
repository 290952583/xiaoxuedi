package com.xiaoxuedi.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UsersEntity implements UserDetails {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    @JoinColumn(name = "id")
    private String id;
    @JoinColumn(nullable = false)
    @Enumerated(EnumType.STRING)
    private UsersEntity.AuthStatus authStatus = UsersEntity.AuthStatus.NOT;
    @Transient
    private List<SimpleGrantedAuthority> authorities;

    @JoinColumn(name = "balance")
    private BigDecimal balance;

    @JoinColumn(name = "create_time")
    private Date createTime = new Date();

    @JoinColumn(name = "enabled")
    private boolean enabled = true;

    @JoinColumn(name = "id_card")
    private String idCard;

    @JoinColumn(name = "open_id")
    private String openId;


    @JoinColumn(name = "session_key ")
    private String sessionKey ;

    @JoinColumn(name = "third_session_key ")
    private String thirdSessionKey ;

    @JoinColumn(name = "invitation_code")
    private String invitationCode;

    @JoinColumn(name = "invitation_count")
    private int invitationCount;

    @JoinColumn(name = "mobile", nullable = false)
    @Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\d{8}$")
    private String mobile;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "password", nullable = false)
    private String password;

    @JoinColumn(name = "sex")
    private String sex;

    @JoinColumn(name = "username")
    private String username;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private SchoolEntity school;

    @JoinColumn(name = "photo")
    private String photo;

    @Transient
    private boolean accountNonExpired = true;
    @Transient
    private boolean accountNonLocked = true;
    @Transient
    private boolean credentialsNonExpired = true;

    public static String getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UsersEntity) {
            UsersEntity user = (UsersEntity) principal;
            return user.getId();
        }
        return null;
    }

    public static UsersEntity getUser() {
        UsersEntity user = new UsersEntity();
        user.setId(getUserId());
        return user;
    }

    public boolean isAuth() {
        return authStatus == UsersEntity.AuthStatus.PASS;
    }


    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public UsersEntity fromEntity(UsersEntity user) {
        return new UsersEntity();
    }

    public enum AuthStatus {
        NOT,
        WAIT,
        PASS
    }

}
