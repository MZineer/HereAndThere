package com.mz.hat.dao;

import com.mz.hat.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo> list();
    Integer write(BoardVo boardVo);

    Integer modify(BoardVo boardVo);

    Integer delete(BoardVo boardVo);
}
