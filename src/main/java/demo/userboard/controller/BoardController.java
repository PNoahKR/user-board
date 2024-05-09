package demo.userboard.controller;

import demo.userboard.dto.request.PostRequestDto;
import demo.userboard.global.annotation.SessionAuth;
import demo.userboard.global.common.auth.SessionLoginInfo;
import demo.userboard.global.common.response.CommonResponse;
import demo.userboard.global.common.util.ApiResponseUtil;
import demo.userboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public CommonResponse<Long> post(@RequestBody PostRequestDto postRequestDto,
                                     @SessionAuth SessionLoginInfo sessionLoginInfo) {

        return ApiResponseUtil.success(boardService.post(sessionLoginInfo.getId(), postRequestDto));
    }
}
