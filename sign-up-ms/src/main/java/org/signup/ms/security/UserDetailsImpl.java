package org.signup.ms.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.signup.ms.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private int id;

    private String email;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(Users user){
        List<GrantedAuthority> authorities= user.getRoles().stream().
                map(roles -> new SimpleGrantedAuthority(roles.getName().name())).
                collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUser_id(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
