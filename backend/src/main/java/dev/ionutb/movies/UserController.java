package dev.ionutb.movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<User> users = userService.allUsers();
        List<UserResponseDTO> responseDTOs = users.stream()
                .map(UserMapper::mapToResponseDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserResponseDTO responseDTO = UserMapper.mapToResponseDTO(user); // You need to define the UserMapper class.
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{userId}/favorites")
    public ResponseEntity<String> addToFavorites(@PathVariable String userId, @RequestBody String movieTitle) {
        Optional<User> optionalUser = userRepository.findById(new ObjectId(userId));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<String> favList = user.getFavList();
            favList.add(movieTitle);
            user.setFavList(favList);
            userRepository.save(user);
            return ResponseEntity.ok("Movie added to favorites");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        Optional<User> existingUser = userRepository.findByUsername(newUser.getUsername());

        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username already exists");
        }

        User createdUser = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public  ResponseEntity<String> loginUser(@RequestBody User loginUser){
        boolean isAuthenticated = userService.authenticateUser(loginUser);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @DeleteMapping("/{userId}/favorites")
    public ResponseEntity<String> removeFromFavorites(@PathVariable String userId, @RequestParam String movieTitle) {
        Optional<User> optionalUser = userRepository.findById(new ObjectId(userId));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<String> favList = user.getFavList();

            if (favList.remove(movieTitle)) {
                user.setFavList(favList);
                userRepository.save(user);
                return ResponseEntity.ok("Movie removed from favorites");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found in favorites");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


    }
