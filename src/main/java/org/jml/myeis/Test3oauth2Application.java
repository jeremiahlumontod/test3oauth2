package org.jml.myeis;

import org.jml.myeis.config.CustomUserDetails;
import org.jml.myeis.entities.Role;
import org.jml.myeis.entities.User;
import org.jml.myeis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@SpringBootApplication
public class Test3oauth2Application {

	public static void main(String[] args) {
		SpringApplication.run(Test3oauth2Application.class, args);
	}

    @Autowired
    public void authenticationManager2(AuthenticationManagerBuilder builder, UserRepository repo) throws Exception {

	    if(repo.count()==0) {
	        repo.save(new User("user","user",Arrays.asList(new Role("USER"),new Role("ACTUATOR"))));
        }

        builder.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                return new CustomUserDetails(repo.findByUsername(s));
            }
        });
    }

}
