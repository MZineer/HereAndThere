package com.mz.hat.dao;

import com.mz.hat.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryVo> list();
}
