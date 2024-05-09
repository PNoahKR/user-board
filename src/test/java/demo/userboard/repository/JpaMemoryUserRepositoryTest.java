package demo.userboard.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JpaMemoryUserRepositoryTest {
//    @Autowired
//    private JpaMemoryUserRepository repository;
//
//    @Test
//    public void save() {
//        User user = User.builder()
//                .name("지우")
//                .age(28)
//                .email("wldn@hi.com")
//                .password("0818")
//                .nickname("Noah")
//                .gender(Gender.M)
//                .build();
//
//
//        repository.save(user);
//
//        User user1 = repository.findById(user.getId()).get();
//        Assertions.assertThat(user).isEqualTo(user1);
//    }
//
//    @Test
//    public void findByEmail() {
//        User user = User
//                .builder()
//                .name("지우")
//                .age(28)
//                .email("wldn@hi.com")
//                .password("0818")
//                .nickname("Noah")
//                .gender(Gender.M)
//                .build();
//
//        repository.save(user);
//
//        User result = repository.findByEmail(user.getEmail()).get();
//        Assertions.assertThat(user).isEqualTo(result);
//    }
}