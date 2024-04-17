
package demo.userboard.service;

import demo.userboard.domain.Gender;
import demo.userboard.domain.User;
import demo.userboard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;


    @Test
    void join() {
        //given
        User user = new User("박지우", "noah", "qkrwldn0818@naver.com", "1234", Gender.M, 28);

        //when
        userService.join(user);

        //then
        User user1 = userService.findUser(user.getId()).get();
        Assertions.assertThat(user.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    void findByEmail() {
        User user = new User();
        user.setName("hi");
        user.setNickname("no");
        user.setPassword("12");
        user.setEmail("asd@asd.com");
        userRepository.save(user);

        User result = userRepository.findByEmail(user.getEmail()).get();
        Assertions.assertThat(user).isEqualTo(result);
    }

}
