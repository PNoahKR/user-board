package demo.userboard.dto.response;

import demo.userboard.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;

    public static LoginResponseDto from(User user) {
        return new LoginResponseDto(user.getId());
    }
}
