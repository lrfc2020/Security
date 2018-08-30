package com.local.security.entity;

import lombok.Data;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements Serializable, UserDetails {

    @Id
    @Column(name = "u_id")
    private Long id;

    @Column(name = "u_username")
    private String name;

    @Column(name = "u_password")
    private String pwd;

    @ManyToMany(fetch = FetchType.EAGER )//去除掉懒加载
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "u_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "r_id")
            }
    )
    private List<RolesEntity> roles;

    /**
     * 将权限信息添加到权限信息列表中
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<RolesEntity> roles = getRoles();
        for (RolesEntity role:roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
