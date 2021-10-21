package com.servicer.app.repository;


import com.servicer.app.user.authorization_model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long > {
    Privilege findByName(String name);
}


