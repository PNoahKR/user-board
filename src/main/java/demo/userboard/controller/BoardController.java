package demo.userboard.controller;

import demo.userboard.dto.request.BoardUpdateRequestDto;
import demo.userboard.dto.request.PostRequestDto;
import demo.userboard.dto.response.BoardDetailViewResponseDto;
import demo.userboard.global.annotation.SessionAuth;
import demo.userboard.global.common.auth.SessionLoginInfo;
import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board/{boardId}")
    public CommonResponse<BoardDetailViewResponseDto> detailView(@PathVariable("boardId") Long boardId) {
        return ApiResponseUtil.success(boardService.findBoard(boardId));
    }

    @PostMapping("/board")
    public CommonResponse<Long> post(@RequestBody PostRequestDto postRequestDto,
                                     @SessionAuth SessionLoginInfo sessionLoginInfo) {

        return ApiResponseUtil.success(boardService.post(sessionLoginInfo.getId(), postRequestDto));
    }

    @PutMapping("/board/{boardId}")
    public CommonResponse<Long> updateBoard(@PathVariable("boardId") Long boardId,
                                            @RequestBody BoardUpdateRequestDto updateRequestDto,
                                            @SessionAuth SessionLoginInfo sessionLoginInfo) {

        return ApiResponseUtil.success(boardService.boardUpdate(boardId, sessionLoginInfo.getId(), updateRequestDto));
    }
}
