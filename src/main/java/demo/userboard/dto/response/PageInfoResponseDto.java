package demo.userboard.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfoResponseDto<T> {
    private List<T> content;
    private PagingResponseDto paging;
}
