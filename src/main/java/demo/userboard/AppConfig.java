package demo.userboard;

import demo.userboard.repository.JpaMemoryUserRepository;
import demo.userboard.repository.MemoryUserRepository;
import demo.userboard.repository.UserRepository;
import demo.userboard.service.UserService;
import demo.userboard.service.UserServiceImpl;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final EntityManager em;

    public AppConfig(EntityManager em) {
        this.em = em;
    }


    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
//        return new MemoryUserRepository();
        return new JpaMemoryUserRepository(em);
    }
}