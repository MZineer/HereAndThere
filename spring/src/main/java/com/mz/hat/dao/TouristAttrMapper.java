package com.mz.hat.dao;

import com.mz.hat.vo.TouristAttrVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TouristAttrMapper {
    List<TouristAttrVo> list();
    List<TouristAttrVo> search_touristAttr(@Param("word") String word);

    TouristAttrVo search_name(String name);
}
