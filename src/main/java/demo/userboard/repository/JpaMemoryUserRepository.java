package demo.userboard.repository;

import demo.userboard.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JpaMemoryUserRepository implements UserRepository {

    private final EntityManager em;

    public JpaMemoryUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        User user = em.find(User.class, userId);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> result = em.createQuery("select m from UserBoard m where m.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        List<User> result = em.createQuery("select m from UserBoard m where m.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return result.stream().findAny();
    }
}
