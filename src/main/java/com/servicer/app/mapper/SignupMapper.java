package com.servicer.app.mapper;

import com.servicer.app.Dto.SignupDto;
import com.servicer.app.user.User;
import org.springframework.stereotype.Service;

@Service
public class  SignupMapper {

    public User signUpToDto(SignupDto signUpToDto){
        return
    User.builder().address(signUpToDto.getAddress()).firstName(signUpToDto.getFirstName())
                .email(signUpToDto.getEmail()).phoneNumber(signUpToDto.getPhoneNumber()).lastName(signUpToDto.getLastName())
                .password(signUpToDto.getPassword()).imageUrl(signUpToDto.getImageUrl()).build();

    }
}



