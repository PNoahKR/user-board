package demo.userboard.dto.request;

import demo.userboard.domain.Gender;
import demo.userboard.domain.User;
import lombok.*;
import org.springframework.util.Assert;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinRequestDto {

    private String name;

    private String nickname;

    private String email;

    private String password;

    private Gender gender;

    private Integer age;

    public User toEntity() {
        return User
                .builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .gender(gender)
                .age(age)
                .build();
    }
}
