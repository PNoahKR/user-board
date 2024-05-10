package demo.userboard.service;

import demo.userboard.dto.request.PostRequestDto;

public interface BoardService {

    Long post(Long userId, PostRequestDto requestDto);

}
