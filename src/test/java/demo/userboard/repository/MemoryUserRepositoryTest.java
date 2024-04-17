
package demo.userboard.repository;

import demo.userboard.domain.Gender;
import demo.userboard.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class MemoryUserRepositoryTest {

    MemoryUserRepository repository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        User user = new User();
        user.setName("박지우");
        user.setEmail("qkrwldn0818@naver.com");
        user.setGender(Gender.M);
        user.setNickname("noah");
        user.setPassword("tlsepfpffk12!");

        repository.save(user);

        System.out.println(user.getCreatedAt());

        User user1 = repository.findById(user.getId()).get();
        Assertions.assertThat(user).isEqualTo(user1);

    }

    @Test
    public void findByEmail() {
        User user1 = new User();
        user1.setEmail("qkrwldn0818@naver.com");
        repository.save(user1);

        User user2 = new User();
        user2.setEmail("qkrwldn0818@gmail.com");
        repository.save(user2);

        User user = repository.findByEmail(user2.getEmail()).get();
        Assertions.assertThat(user2).isEqualTo(user);
    }

    @Test
    public void findByNickname() {
        User user1 = new User();
        user1.setNickname("noah");
        repository.save(user1);

        User user2 = new User();
        user2.setNickname("zito");
        repository.save(user2);

        User user = repository.findByNickname(user1.getNickname()).get();
        Assertions.assertThat(user1).isEqualTo(user);
    }
}
