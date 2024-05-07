package demo.userboard.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InfoUpdateRequestDto {
    @NotBlank(message = "닉네임은 공백일 수 없습니다.")
    @Size(max = 5, message = "닉네임은 최대 50자여야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 4, max = 60, message = "비밀번호는 8자에서 20자 사이여야 합니다.")
    private String password;
}
