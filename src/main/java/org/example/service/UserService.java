package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    //public void deleteById(int id)

    public void deleteById(int id){ userRepository.deleteById(id);}

    public void updateById(int id, User changedUser) { userRepository.updateById(id, changedUser);
    }

    public User findById(int id) {
        return userRepository.findById(id);
    }
}
