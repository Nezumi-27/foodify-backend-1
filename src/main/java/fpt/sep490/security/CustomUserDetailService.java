package fpt.sep490.security;

import fpt.sep490.entity.User;
import fpt.sep490.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrPhoneNumber) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrPhoneNumber(emailOrPhoneNumber, emailOrPhoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email/phone number: " + emailOrPhoneNumber));

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
