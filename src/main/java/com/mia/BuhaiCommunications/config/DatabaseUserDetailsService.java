package com.mia.BuhaiCommunications.config;

import com.mia.BuhaiCommunications.model.PendingUser;
import com.mia.BuhaiCommunications.model.User;
import com.mia.BuhaiCommunications.repository.PendingUserRepository;
import com.mia.BuhaiCommunications.repository.UserRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Getter
@Slf4j
public class DatabaseUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    private PendingUserRepository pendingUserRepository;

    @Autowired
    public DatabaseUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Username " + username);
        Optional<PendingUser> optional = pendingUserRepository.findByUsername(username);

        if (optional.isPresent()) {
            log.info(optional.get().getActivationCode());
            throw new UsernameNotFoundException(username);
        }
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return new CustomUserDetails(user);
    }

}