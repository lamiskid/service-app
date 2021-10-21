package com.servicer.app.model;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Profile {
    @Id
    private Long id;
    private String name;
    private Long number;
}
