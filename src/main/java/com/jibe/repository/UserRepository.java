package com.jibe.repository;


import com.jibe.entity.User;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;


/**
 *
 * @author Win11
 */
public class UserRepository {
    
   private final Map<Long, User> users = new HashMap<>();
   
   
   public void save(long userId, User user) {
       
       users.put(userId, user);
   }
   
   
   public Optional<User> findUserById(long userId) {
       return Optional.ofNullable(users.get(userId));
   }

   
   public List<User> getAll() {
       return new ArrayList<>(users.values());
   }
   
}
