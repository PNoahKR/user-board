package demo.userboard.service;

import demo.userboard.domain.User;
import demo.userboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public void join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(a -> {throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");});
        userRepository.findByNickname(user.getNickname())
                .ifPresent(a -> {throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");});
    }
}