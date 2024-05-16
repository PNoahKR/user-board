package demo.userboard.dto.response;

import demo.userboard.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllBoardListResponseDto {
    private Long boardId;
    private String title;
    private String writer;

    public static AllBoardListResponseDto from(Board board) {
        return new AllBoardListResponseDto(board.getId(), board.getTitle(), board.getUser().getNickname());
    }
}
