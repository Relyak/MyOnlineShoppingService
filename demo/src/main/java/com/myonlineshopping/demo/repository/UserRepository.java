package com.myonlineshopping.demo.repository;


import com.myonlineshopping.demo.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    ArrayList<User> usuarios = new ArrayList<>();
    public Optional<User> findByEmail(String email){
        return usuarios.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    public Optional<User> findByEmailAndPassword(String email, String password){

        return usuarios.stream().filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password)).findFirst();
    }
    public List<User> findAll(){
        return usuarios;
    };
    public void save(User newUser) {
        newUser.setId(usuarios.size()+1);
        usuarios.add(newUser);
    };


//
//    @Modifying
//    @Query("delete from User where email = :email")
//    void deleteUsersByEmail(@Param("email") String email);
}