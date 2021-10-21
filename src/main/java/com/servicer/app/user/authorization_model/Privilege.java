package com.servicer.app.user.authorization_model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@Data
public class Privilege {

    private Privilege(){

    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Privilege(String name){
        this.name=name;

    }

    @ManyToMany
    private Collection<Role> roles;
}
