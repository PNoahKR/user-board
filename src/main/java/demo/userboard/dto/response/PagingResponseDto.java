package demo.userboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponseDto {
    private int pageNumber;
    private int pageSize;
    private boolean hasNext;
    private int numberOfElements;
}
