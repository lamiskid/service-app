package com.servicer.app.user;


import com.servicer.app.user.UserDto;
import com.servicer.app.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {


    public UserDto mapUserToDto(User user) {

        return UserDto.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).
                address(user.getAddress()).firstName(user.getFirstName()).lastName(user.getLastName())
                .imageUrl(user.getImageUrl())
                .phoneNumber(user.getPhoneNumber()).build();

    }

    public User mapDtoToUser(UserDto userDto){

        return User.builder().id(userDto.getId()).username(userDto.getUsername()).lastName(userDto.getLastName()).firstName(userDto.getFirstName())
                .address(userDto.getAddress()).phoneNumber(userDto.getPhoneNumber()).build();
    }





}
