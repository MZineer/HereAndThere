package com.mz.hat.service;

import com.mz.hat.dao.RouteMapper;
import com.mz.hat.vo.RouteVo;
import com.mz.hat.vo.TogetherVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RouteService {

    @Autowired
    private RouteMapper routeMapper;

    public RouteVo info(int route_id) {
        RouteVo routeVo = routeMapper.info(route_id);
        if (routeVo == null) {
            routeVo = new RouteVo();
        }
        logger.debug("get_vo: {}", routeVo);

        return routeVo;
    }
}
