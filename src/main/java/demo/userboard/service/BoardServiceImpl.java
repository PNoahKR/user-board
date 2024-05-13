package demo.userboard.service;

import demo.userboard.domain.Board;
import demo.userboard.domain.User;
import demo.userboard.dto.request.BoardUpdateRequestDto;
import demo.userboard.dto.request.PostRequestDto;
import demo.userboard.dto.response.BoardDetailViewResponseDto;
import demo.userboard.global.common.response.CustomErrorCode;
import demo.userboard.global.core.exception.CustomException;
import demo.userboard.repository.BoardRepository;
import demo.userboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Long post(Long userId, PostRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_LOGIN_INFO));
        Board board = requestDto.toEntity(user);
        Board saveBoard = boardRepository.save(board);
        return saveBoard.getId();
    }

    @Override
    public BoardDetailViewResponseDto findBoard(Long boardId) {
        return boardRepository.findBoardById(boardId)
                .map(BoardDetailViewResponseDto::from)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_BOARD_INFO));
    }

    @Override
    @Transactional
    public Long boardUpdate(Long boardId, Long userId, BoardUpdateRequestDto updateRequestDto) {
        Board board = boardRepository.findBoardById(boardId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.NOT_FOUND_BOARD_INFO));

        if (!board.getUser().getId().equals(userId)) {
            throw new CustomException(CustomErrorCode.UNAUTHORIZED);
        }

        String newTitle = updateRequestDto.getTitle();
        String newContent = updateRequestDto.getContent();

        if (!StringUtils.hasText(newTitle) && !StringUtils.hasText(newContent)) {
            throw new CustomException(CustomErrorCode.INVALID_FORMAT);
        }

        if (StringUtils.hasText(newTitle)) {
            if (newTitle.length() > 50) {
                throw new CustomException(CustomErrorCode.INVALID_FORMAT);
            }
            board.updateTitle(newTitle);
        }

        if (StringUtils.hasText(newContent)) {
            board.updateContent(newContent);
        }
        return boardId;
    }
}
