package demo.userboard.service;

import demo.userboard.domain.User;

import java.util.Optional;

public interface UserService {
    void join(User user);

    Optional<User> findUser(Long userId);
}
