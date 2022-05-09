package com.demo.service.impl;

import com.demo.service.MyService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)principal;
            Collection<? extends GrantedAuthority> authorities =
                    userDetails.getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority("ROLE_管理员"));
        }
        return false;
    }
}
