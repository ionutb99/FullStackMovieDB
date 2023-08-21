package dev.ionutb.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public User createUser(User newUser){
        return userRepository.save(newUser);
    }

    public boolean authenticateUser(User loginUser) {
        Optional<User> userOptional = userRepository.findByUsername(loginUser.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(loginUser.getPassword());
        }
        return false;
    }

}
