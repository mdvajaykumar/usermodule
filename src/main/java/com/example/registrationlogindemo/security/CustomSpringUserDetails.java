package com.example.registrationlogindemo.security;

import com.example.registrationlogindemo.models.Role;
import com.example.registrationlogindemo.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@JsonDeserialize(as = CustomSpringUserDetails.class)
@NoArgsConstructor
public class CustomSpringUserDetails implements UserDetails {

    private User user;
    public CustomSpringUserDetails(User user){
        this.user =user;
    }


    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<CustomSpringGrantedAuthority> customSpringGrantedAuthorityList =new ArrayList<>();
        for(Role role : user.getRoles()){
            customSpringGrantedAuthorityList.add(new CustomSpringGrantedAuthority(role));
        }
        return customSpringGrantedAuthorityList;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
