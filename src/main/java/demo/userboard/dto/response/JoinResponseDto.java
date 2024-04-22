package demo.userboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinResponseDto {

    private String message;
    private Long id;
    private int statusCode;
}
