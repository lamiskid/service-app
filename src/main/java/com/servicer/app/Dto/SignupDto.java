package com.servicer.app.Dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class SignupDto {


    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String Address;
    private String username;
    private String password;
    private String imageUrl;

}
