package demo.userboard;

import demo.userboard.repository.MemoryUserRepository;
import demo.userboard.repository.UserRepository;
import demo.userboard.service.UserService;
import demo.userboard.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }
}
