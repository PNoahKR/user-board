package demo.userboard.dto.response;

import demo.userboard.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDetailViewResponseDto {

    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String userNickname;
    private String content;

    public static BoardDetailViewResponseDto from(Board board) {
        return new BoardDetailViewResponseDto(
                board.getTitle(),
                board.getCreatedAt(),
                board.getModifiedAt(),
                board.getUser().getNickname(),
                board.getContent()
        );
    }

}
