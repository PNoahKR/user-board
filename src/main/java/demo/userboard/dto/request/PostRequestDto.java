package demo.userboard.dto.request;

import demo.userboard.domain.Board;
import demo.userboard.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostRequestDto {

    private String title;
    private String content;

    public Board toEntity(User user) {
        return Board
                .builder()
                .user(user)
                .title(title)
                .content(content)
                .status(true)
                .build();
    }
}
