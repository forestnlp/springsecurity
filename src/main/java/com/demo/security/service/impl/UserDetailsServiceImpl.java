package com.demo.security.service.impl;

import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        com.demo.entity.User user = userMapper.selectByUserName(s);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        UserDetails userDetails = new User(user.getUserName(), user.getPassword(),AuthorityUtils.commaSeparatedStringToAuthorityList("jczl,admin2,ROLE_ADMIN"));
        return userDetails;
    }
}
