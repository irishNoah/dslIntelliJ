package com.example.dslIntelliJ.service;

import com.example.dslIntelliJ.entity.Board;
import com.example.dslIntelliJ.entity.User;
import com.example.dslIntelliJ.respository.BoardRepository;
import com.example.dslIntelliJ.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    /* 내용 작성 */
    // Board 저장 이전에, User의 id가 실제 DB에 있는 id인지 유효성 검사 이후에 저장
    public Board saveBoard(Board board) {
        Long userId = board.getUser().getId();
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 사용자가 존재하지 않습니다.");
        }

        // 사용자 검증을 통과하면 게시글 저장
        board.setUser(userOptional.get()); // 실제로 존재하는 사용자 설정
        return boardRepository.save(board);
    }

    public List<Board> findAllBoards(){
        return boardRepository.findAll();
    }

    public Board findBoardById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board updateBoard(@PathVariable("id") Long id, @RequestBody Board BoardDetails) {
        Optional<Board> BoardOptional = boardRepository.findById(id);
        if(BoardOptional.isPresent()) {
            Board Board = BoardOptional.get();
            Board.setTitle(BoardDetails.getTitle());
            Board.setContent(BoardDetails.getContent());

            return boardRepository.save(Board);
        } else {
            return null;
        }
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
