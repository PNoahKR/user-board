package demo.userboard.dto.response;

import demo.userboard.domain.Gender;
import demo.userboard.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private String message;
    private int statusCode;

    private String name;
    private String nickname;
    private String email;
    private Gender gender;
    private Integer age;

    public static LoginResponseDto from(User user) {
        return new LoginResponseDto("로그인 성공", 200, user.getName(), user.getNickname(), user.getEmail(), user.getGender(), user.getAge());
    }
}
