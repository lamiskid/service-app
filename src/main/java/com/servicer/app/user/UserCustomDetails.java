package com.servicer.app.user;

import com.servicer.app.user.authorization_model.Privilege;
import com.servicer.app.user.authorization_model.Role;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Service
public class UserCustomDetails  extends User implements UserDetails{




}



