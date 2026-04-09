package com.jibe.repository;


import com.jibe.entity.User;
import com.jibe.repository.impl.Repository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.ArrayList;


/**
 *
 * @author Win11
 */
public class UserRepository implements Repository<User, Long> {
    
   private final Map<Long, User> users = new HashMap<>();

    public UserRepository() {
    }

    @Override
   public void save(User user, Long userId) {
       users.put(userId, user);
   }

   public boolean isEmailExists(String email) {
       return users.values()
               .stream()
       .anyMatch(u -> u.getEmail().equals(email));
   }
   
   public Optional<User> findUserById(long userId) {
       return Optional.ofNullable(users.get(userId));
   }

   
   public List<User> getAll() {
       return new ArrayList<>(users.values());
   }
   
}
