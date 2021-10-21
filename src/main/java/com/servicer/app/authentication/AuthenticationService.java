package com.servicer.app.authentication;

import com.servicer.app.Dto.LoginRequest;
import com.servicer.app.Dto.SignupDto;
import com.servicer.app.repository.PrivilegeRepository;
import com.servicer.app.repository.RoleRepository;
import com.servicer.app.user.User;
import com.servicer.app.user.UserService;
import com.servicer.app.user.UserServiceImpl;
import com.servicer.app.repository.UserRepository;
import com.servicer.app.security.JwtAuthentication;
import com.servicer.app.user.authorization_model.Privilege;
import com.servicer.app.user.authorization_model.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

   private final  UserServiceImpl userServiceImpl;

    private final JwtAuthentication jwtAuthentication;



    private final RoleRepository roleRepository;


    public void signup(SignupDto signupDto)  {

        if(userRepository.existsByUsername(signupDto.getUsername())){
            throw new RuntimeException("User Already exists");
        }else {
            User user =createUser(signupDto);

            user.setUsername(signupDto.getUsername());
            user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
            user.setEmail(signupDto.getEmail());
            user.setPhoneNumber(signupDto.getPhoneNumber());
            user.setFirstName(signupDto.getFirstName());
            user.setLastName(signupDto.getLastName());
            user.setAddress(signupDto.getAddress());

            userRepository.save(user);
        }

    }

    public void  login(LoginRequest loginRequest, HttpServletResponse response){
        UserDetails userDetails = userServiceImpl.loadUserByUsername(loginRequest.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.addHeader("Authorization","Bearer "+jwtAuthentication.generateToken(authentication));

    }

    private User createUser(SignupDto signupDto) {


        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");


        createRoleIfNotFound("ROLE_USER", Arrays.asList(writePrivilege, readPrivilege));

        Role userRole = roleRepository.findByName("ROLE_USER");


        User user=new User();
        user.setRoles(Arrays.asList(userRole));
        user.setEnabled(true);

        return user;
    }
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }



    public void createAdmin() {

        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        User user=new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);


    }


}
