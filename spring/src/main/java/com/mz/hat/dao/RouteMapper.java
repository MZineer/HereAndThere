package com.mz.hat.dao;

import com.mz.hat.vo.RouteVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteMapper {
    Integer write(RouteVo routeVo);

    RouteVo info(int id);
}
