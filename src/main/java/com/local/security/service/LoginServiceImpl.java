package com.local.security.service;

import com.local.security.dao.UserRepository;
import com.local.security.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
	    Optional<UserEntity> userEntity = userRepository.findByName(s);
        if(!userEntity.isPresent()){
            throw  new UsernameNotFoundException("用户:"+s+",无法找到!");
        }
        System.out.println(userEntity);
        return userEntity.get();
    }
}
