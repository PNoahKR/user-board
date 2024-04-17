package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void join(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }
}