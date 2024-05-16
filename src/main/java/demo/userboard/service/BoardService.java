package demo.userboard.service;

import demo.userboard.dto.request.BoardDeleteRequestDto;
import demo.userboard.dto.request.BoardUpdateRequestDto;
import demo.userboard.dto.request.PostRequestDto;
import demo.userboard.dto.response.AllBoardListResponseDto;
import demo.userboard.dto.response.BoardDetailViewResponseDto;

import java.util.List;

public interface BoardService {

    Long post(Long userId, PostRequestDto requestDto);

    BoardDetailViewResponseDto findBoard(Long boardId);

    Long boardUpdate(BoardUpdateRequestDto updateRequestDto);

    Long boardDelete(BoardDeleteRequestDto deleteRequestDto);

    List<AllBoardListResponseDto> findAllBoard();
}
