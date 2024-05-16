package demo.userboard.repository;

import demo.userboard.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);

    Optional<Board> findBoardById(Long boardId);

    void delete(Long boardId);

    List<Board> findAll();
}
