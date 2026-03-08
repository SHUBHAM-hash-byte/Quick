package com.quick.web.repo;

import com.quick.web.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.Optional;
import java.util.OptionalInt;



public interface RegisterRepo extends JpaRepository<User ,Integer> {

   Optional<User> findByEmail(String email);
}
