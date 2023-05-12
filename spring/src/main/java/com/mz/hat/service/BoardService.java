package com.mz.hat.service;

import com.mz.hat.dao.BoardMapper;
import com.mz.hat.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    public List<BoardVo> list() {
        List<BoardVo> boardVos = boardMapper.list();
        logger.debug("postVos.size: {}", boardVos.size());
        for(BoardVo boardVo : boardVos) {
            logger.debug(">>>> post: {}", boardVo);
        }
        return boardVos;
    }

    public int write(BoardVo boardVo) {
        int affectRow = boardMapper.write(boardVo);
        logger.debug("INSERT affectRow: {}", affectRow);
        return affectRow;
    }

    public int modify(BoardVo boardVo) {
        int affectRow = boardMapper.modify(boardVo);
        logger.debug("UPDATE affectRow: {}", affectRow);
        return affectRow;
    }

    public int delete(BoardVo boardVo) {
        int affectRow = boardMapper.delete(boardVo);
        logger.debug("DELETE affectRow: {}", affectRow);
        return affectRow;
    }
}
