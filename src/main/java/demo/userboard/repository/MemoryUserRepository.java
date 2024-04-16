package demo.userboard.repository;

import demo.userboard.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryUserRepository implements UserRepository {

    private static Map<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public void save(User user) {
        user.setId(sequence++);
        user.setCreatedAt(LocalDateTime.now());
        store.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(store.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.values().stream()
                .filter(user -> user.getEmail().equals(email)).findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return store.values().stream()
                .filter(user -> user.getNickname().equals(nickname)).findAny();
    }

    public void clearStore() {
        store.clear();
    }
}
