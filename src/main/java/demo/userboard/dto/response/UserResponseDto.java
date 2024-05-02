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
public class UserResponseDto {

    private String name;
    private String email;
    private String nickname;
    private Gender gender;
    private Integer age;

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getName(), user.getEmail(), user.getNickname(), user.getGender(), user.getAge());
    }
}
