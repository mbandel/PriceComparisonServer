package com.pc.user;

import com.pc.poster.Poster;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private PasswordEncoder encoder;
    private static Logger logger = Logger.getLogger(RegisterController.class.getName());

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public boolean registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            logger.info(userDto.getEmail() + " current email is already used");
            return false;
        }
        if (userRepository.existsByUsername(userDto.getUsername())){
            logger.info(userDto.getUsername() + " username is already used");
            return false;
        }
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getUsername(), userDto.getEmail(), encoder.encode(userDto.getPassword()));
        userRepository.save(user);
        logger.info(user.getEmail() + " user registered");
        return true;
    }

    public User findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        return user.get();
    }
    
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with email: " + email + " not found");
        }
        return user.get();
    }

    public User findUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            throw new UserNotFoundException("User: " + username + " not found");
        }
        return user.get();
    }

    public List<Poster> getUserProducts(Long id){
        User user = findUserById(id);
        return user.getPosters();
    }

    public User editUser(Long id, UserDto userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        user.get().updateValues(userRequest);
        logger.info("Updated user with id: " + id);
        return userRepository.save(user.get());
    }

    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        logger.info("Deleted user with id: " + id);
        userRepository.delete(user.get());
    }

    /*
    public void addProduct(Product product, Long id){
        Optional<User> user = userRepository.findById(id);
        user.get().addProduct(product);

    }

    public List<Product> findUserProductsById(Long id) {
        return findUserById(id).getProducts();
    }

     */

    /*
    @Override
    public List<Rating> findUserRatingsById(Long id) {
        return findUserById(id).getRatings();
    }
     */

}