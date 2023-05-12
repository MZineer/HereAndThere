package com.mz.hat.dao;

import com.mz.hat.vo.RegionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionMapper {
    List<RegionVo> list();
}
