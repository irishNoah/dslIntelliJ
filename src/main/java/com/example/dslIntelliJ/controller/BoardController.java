package com.example.dslIntelliJ.controller;

import com.example.dslIntelliJ.entity.Board;
import com.example.dslIntelliJ.service.BoardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;


    @PostMapping("/register-board")
    public Board createBoard(@RequestBody Board board) {
        return boardService.saveBoard(board);
    }

    @GetMapping("/get-board")
    public List<Board> getAllBaords(){
        return boardService.findAllBoards();
    }

    @Transactional
    @GetMapping("/get-board/{id}")
    public Board findBoardById(@PathVariable("id") Long id) {
        Board board = boardService.findBoardById(id);

        return board;
    }

    @Transactional
    @PutMapping("/put-board/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable("id") Long id, @RequestBody Board boardDetails){
        Board updateBoard = boardService.updateBoard(id, boardDetails);

        if(boardDetails != null) {
            return ResponseEntity.ok(updateBoard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-board/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
    }
}
