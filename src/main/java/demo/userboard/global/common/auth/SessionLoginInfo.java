package demo.userboard.global.common.auth;

import demo.userboard.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionLoginInfo {

    private Long id;
    private String nickname;
    private String email;

    public static SessionLoginInfo from(User user) {
        return new SessionLoginInfo(
                user.getId(),
                user.getNickname(),
                user.getEmail()
        );
    }
}
