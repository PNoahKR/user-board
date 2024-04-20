package demo.userboard.service;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {
//
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Test
//    void join() {
//        //given
//        JoinRequestDto user = JoinRequestDto
//                .builder()
//                .name("지우")
//                .age(28)
//                .email("wldn@hi.com")
//                .password("0818")
//                .nickname("jiwoo")
//                .gender(Gender.M)
//                .build();
//
//        //when
//        User joinUser = userService.join(user);
//
//        //then
//        User user1 = userService.findUser(joinUser.getId()).get();
//        Assertions.assertThat(joinUser.getEmail()).isEqualTo(user1.getEmail());
//    }
//
//    @Test
//    void validateDuplicateUserTest() {
//        JoinRequestDto user1 = JoinRequestDto
//                .builder()
//                .name("지우")
//                .age(28)
//                .email("wldn@hi.com")
//                .password("0818")
//                .nickname("jiwoo")
//                .gender(Gender.M)
//                .build();
//
//        JoinRequestDto user2 = JoinRequestDto
//                .builder()
//                .name("노아")
//                .age(28)
//                .email("wldn@hi.com")
//                .password("0818")
//                .nickname("Noah")
//                .gender(Gender.M)
//                .build();
//
//        userService.join(user1);
//
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.join(user2));
//        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이메일 입니다.");
//    }
//
//    @Test
//    void validateDuplicateUserTest2() {
//        JoinRequestDto user1 = JoinRequestDto
//                .builder()
//                .name("지우")
//                .age(28)
//                .email("wldn@hi.net")
//                .password("0818")
//                .nickname("Noah")
//                .gender(Gender.M)
//                .build();
//
//        JoinRequestDto user2 = JoinRequestDto
//                .builder()
//                .name("노아")
//                .age(28)
//                .email("wldn@hi.com")
//                .password("0818")
//                .nickname("Noah")
//                .gender(Gender.M)
//                .build();
//
//        userService.join(user1);
//
//        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userService.join(user2));
//        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 닉네임 입니다.");
//    }
}
