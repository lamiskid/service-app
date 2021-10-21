package com.servicer.app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {


        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
  @GetMapping("/getUser/{id}")
  public ResponseEntity<UserDto> getUser(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
  }



  @PutMapping ("/{id}/edit")
    public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto ,@PathVariable Long id)
          throws Exception{
        try{
            userService.editUser(userDto,id);

        }catch (Exception ex){

        }

        return ResponseEntity.ok(userDto);

  }


}

