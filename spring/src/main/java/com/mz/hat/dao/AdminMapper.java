package com.mz.hat.dao;

import com.mz.hat.vo.TouristAttrVo;
import com.mz.hat.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    List<UserVo> get_user_list();

    List<TouristAttrVo> get_tour_list();
    Integer add_tour_list(TouristAttrVo touristAttrVo);
}
