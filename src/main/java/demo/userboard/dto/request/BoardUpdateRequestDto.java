package demo.userboard.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequestDto {

    private String title;
    private String content;
    private Long boardId;
    private Long userId;
}
