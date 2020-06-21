package com.pc.jwt;

import java.util.ArrayList;
import java.util.Optional;

import com.pc.user.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    
	private final UserRepository userRepository;
    
	public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<com.pc.user.User> user = userRepository.findByEmail(email);
		if (user != null) {
			return new User(user.get().getEmail(),user.get().getPassword(), new ArrayList<>());
		} 
		else {
			throw new UsernameNotFoundException("User with email: " + email + " not found");
		}
	}

}