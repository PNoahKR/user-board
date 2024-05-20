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

    public static <T> PageInfoResponseDto<T> of(int page, int size, List<T> contents) {
        List<T> subList = contents.subList(0, Math.min(contents.size(), size));

        PagingResponseDto pagingResponseDto = new PagingResponseDto(
                page,
                size,
                contents.size() > size,
                subList.size());
        return new PageInfoResponseDto<>(subList, pagingResponseDto);
    }
}
