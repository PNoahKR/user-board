package demo.userboard.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void User_Name_Null_오류() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> User
                        .builder()
                        .name("")
                        .email("wldn@hi.com")
                        .password("0818")
                        .nickname("Noah")
                        .build());
        assertThat(e.getMessage()).isEqualTo("이름을 입력해주세요.");
    }

    @Test
    public void User_Nickname_Null_오류() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> User
                        .builder()
                        .name("지우")
                        .email("wldn@hi.com")
                        .password("0818")
                        .nickname("")
                        .build());
        assertThat(e.getMessage()).isEqualTo("닉네임을 입력해주세요.");
    }

    @Test
    public void User_email_Null_오류() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> User
                        .builder()
                        .name("지우")
                        .email("")
                        .password("0818")
                        .nickname("Noah")
                        .build());
        assertThat(e.getMessage()).isEqualTo("이메일을 입력해주세요.");
    }

    @Test
    public void User_password_Null_오류() {

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> User
                        .builder()
                        .name("지우")
                        .email("wldn@hi.com")
                        .password("")
                        .nickname("Noah")
                        .build());
        assertThat(e.getMessage()).isEqualTo("비밀번호를 입력해주세요.");
    }

    @Test
    public void UpdateUserInfo_password_Null_오류() {
        User user = User
                .builder()
                .name("지우")
                .email("wldn@hi.com")
                .password("1234")
                .nickname("Noah")
                .build();

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> user.updateUserInfo("zito", ""));

        assertThat(e.getMessage()).isEqualTo("비밀번호를 입력해주세요.");
    }

    @Test
    public void UpdateUserInfo_nickname_Null_오류() {
        User user = User
                .builder()
                .name("지우")
                .email("wldn@hi.com")
                .password("1234")
                .nickname("Noah")
                .build();

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> user.updateUserInfo("", "4321"));

        assertThat(e.getMessage()).isEqualTo("닉네임을 입력해주세요.");
    }

}