package com.mz.hat.service;

import com.mz.hat.dao.RouteMapper;
import com.mz.hat.dao.TogetherMapper;
import com.mz.hat.dao.TouristAttrMapper;
import com.mz.hat.dao.UserMapper;
import com.mz.hat.vo.RouteVo;
import com.mz.hat.vo.TogetherVo;
import com.mz.hat.vo.TouristAttrVo;
import com.mz.hat.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Service
public class TogetherService {
    @Autowired
    private TogetherMapper togetherMapper;

    @Autowired
    private TouristAttrMapper touristAttrMapper;

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private UserMapper userMapper;

    public List<TogetherVo> list() {
        List<TogetherVo> togetherVos = togetherMapper.list();
        logger.debug("togetherVos.size: {}", togetherVos.size());

        return togetherVos;
    }

    public int write(TogetherVo togetherVo) {
        RouteVo routeVo = new RouteVo();

        UserVo userVo;
        userVo = userMapper.get_user(String.valueOf(togetherVo.getUser_id()));
        routeVo.setUser_id(userVo.getId());
        routeVo.setTourist_attrs_list(togetherVo.getRoutes());

        int route_affectRow = routeMapper.write(routeVo);
        int row_id = routeVo.getId();

        togetherVo.setRoute_id(row_id);
        int affectRow = togetherMapper.write(togetherVo) + route_affectRow;
        logger.debug("INSERT affectRow: {}", affectRow);

        return affectRow;
    }

    public TogetherVo info(TogetherVo togetherVo) {
        TogetherVo get_vo = togetherMapper.info(togetherVo);
        if (get_vo == null) {
            get_vo = new TogetherVo();
        }
        logger.debug("get_vo: {}", get_vo);

        return get_vo;
    }

    public TogetherVo detail(int id) {
        TogetherVo get_vo = togetherMapper.detail(id);
        if (get_vo == null) {
            get_vo = new TogetherVo();
        }
        logger.debug("get_vo: {}", get_vo);

        return get_vo;
    }

    public List<TouristAttrVo> touristAttrList(String word) {
        List<TouristAttrVo> touristAttrVoList = touristAttrMapper.search_touristAttr(word);
        logger.debug("touristAttrVoList.size: {}", touristAttrVoList.size());
        for(TouristAttrVo touristAttrVo: touristAttrVoList) {
            logger.debug(">>>> touristAttrVo: {}", touristAttrVo);
        }

        return touristAttrVoList;
    }
}
