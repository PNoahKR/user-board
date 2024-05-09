package demo.userboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoUpdateRequestDto {
    private String nickname;
    private String password;
}
