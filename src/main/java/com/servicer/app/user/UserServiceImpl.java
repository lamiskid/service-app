package com.servicer.app.user;

import com.servicer.app.user.User;
import com.servicer.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User  user = null;
        user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(" cannot fond user " + username));

        return  user;
    }
}


