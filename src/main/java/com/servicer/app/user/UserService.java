package com.servicer.app.user;

import com.servicer.app.Dto.SignupDto;
import com.servicer.app.repository.PrivilegeRepository;
import com.servicer.app.repository.RoleRepository;
import com.servicer.app.repository.UserRepository;
import com.servicer.app.user.authorization_model.Privilege;
import com.servicer.app.user.authorization_model.Role;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class UserService {


    private final PrivilegeRepository privilegeRepository;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;



    private final UserMapper userMapper;





    /*
     * This method is used getting  user from db
     * */
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapUserToDto)
                .collect(toList());
    }

    @Transactional
    public void editUser(UserDto userDto ,Long id) throws Exception {

        if(userRepository.existsById(id)) {
            User user1 = userMapper.mapDtoToUser(userDto);

            userRepository.save(user1);
        }else{
            throw new Exception("User with " +id+"Does not exist");
        }




    }


    public UserDto getUser(Long id) throws Exception {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new Exception("User not found with ID - " + id));
        return userMapper.mapUserToDto(user);
    }





}
