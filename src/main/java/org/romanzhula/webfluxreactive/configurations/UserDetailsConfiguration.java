package org.romanzhula.webfluxreactive.configurations;

import lombok.RequiredArgsConstructor;
import org.romanzhula.webfluxreactive.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class UserDetailsConfiguration {

    private final UserRepository userRepository;

//    @Bean
//    public ReactiveUserDetailsService userDetailsService() {
//        return (username) -> {
//            return userRepository.findByUsername(username)
//                    .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
//                    .map(user -> (UserDetails) user);
//        };
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails user = (UserDetails) userRepository.findByUsername(username);
                if (user == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                return user;
            }
        };
    }
}
