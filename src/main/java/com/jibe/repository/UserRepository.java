package com.jibe.repository;


import com.jibe.model.User;

import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Win11
 */
public class UserRepository {
    
   private final Map<Long, User> users = new HashMap<>();
   
   
   public void save(long userId, User user) {
       
       users.put(userId, user);
   }
   
   
   public  Optional<User> findUserById(long userId) {
       return Optional.ofNullable(users.get(userId));
   }
   
   
   public List<User> getAll() {
       return new ArrayList<>(users.values());
   }
   
}
