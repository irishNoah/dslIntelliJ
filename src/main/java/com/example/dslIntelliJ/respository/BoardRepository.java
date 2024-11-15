package com.example.dslIntelliJ.respository;

import com.example.dslIntelliJ.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 수정 1 commit
    // 수정 2
    // 수정 3
    // 수정 4
    // 수정 5
}
