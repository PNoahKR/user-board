
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
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

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
    void validateDuplicateUserTest() {
        User user1 = User
                .builder()
                .name("지우")
                .age(28)
                .email("wldn@hi.com")
                .password("0818")
                .nickname("jiwoo")
                .gender(Gender.M)
                .build();

        User user2 = User
                .builder()
                .name("노아")
                .age(28)
                .email("wldn@hi.com")
                .password("0818")
                .nickname("Noah")
                .gender(Gender.M)
                .build();

        userService.join(user1);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.join(user2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일 입니다.");
    }
}
