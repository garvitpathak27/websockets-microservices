package Application.websocket.service;

import Application.websocket.Entity.User;
import Application.websocket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;

    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public User createUser(User user){
        return userRepository.save(user);
    }
}
