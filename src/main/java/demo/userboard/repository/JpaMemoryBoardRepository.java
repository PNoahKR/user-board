package demo.userboard.repository;

import demo.userboard.domain.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemoryBoardRepository implements BoardRepository {

    private final EntityManager em;

    @Override
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public Optional<Board> findBoardById(Long boardId) {
        Board board = em.find(Board.class, boardId);
        return Optional.ofNullable(board)
                .filter(Board::isStatus);
    }

    @Override
    @Transactional
    public void delete(Long boardId) {
        Board board = em.find(Board.class, boardId);
        board.changeStatus(false);
    }

    @Override
    public List<Board> getPagedBoard(int page, int size) {
        List<Board> resultList = em.createQuery("select b from Board b where b.status = true order by b.id desc", Board.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size + 1)
                .getResultList();
        return resultList;
    }
}
