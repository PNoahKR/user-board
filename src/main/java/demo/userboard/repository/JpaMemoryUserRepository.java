package demo.userboard.repository;

import demo.userboard.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemoryUserRepository implements UserRepository {

    private final EntityManager em;

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
        List<User> result = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        User result = em.createQuery("select u from User u where u.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public Optional<User> findByEmailOrNickname(String email, String nickname) {
        List<User> result = em.createQuery("select u from User u where u.email = :email OR u.nickname = :nickname", User.class)
                .setParameter("email", email)
                .setParameter("nickname", nickname)
                .getResultList();
        return result.stream().findAny();
    }
}
