package com.mz.hat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mz.hat.dao.AdminMapper;
import com.mz.hat.support.utill.JsonLoader;
import com.mz.hat.vo.BoardVo;
import com.mz.hat.vo.ImageVo;
import com.mz.hat.vo.TouristAttrVo;
import com.mz.hat.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ObjectMapper objectMapper;

    public List<UserVo> get_user_list() {
        List<UserVo> userVos = adminMapper.get_user_list();
        logger.debug("userVos.size: {}", userVos.size());
        for(UserVo userVo: userVos) {
            logger.debug(">>>> userVo: {}", userVo);
        }

        return userVos;
    }

//    public List<UserVo> list(int offset, int page_size) {
//        List<UserVo> userVos = adminMapper.load_user_list();
//        logger.debug("userVos.size: {}", userVos.size());
//        for(UserVo userVo : userVos) {
//            logger.debug(">>>> userVo: {}", userVo);
//        }
//        return userVos;
//    }

    public List<TouristAttrVo> get_tour_list() {
        List<TouristAttrVo> touristAttrVoList = adminMapper.get_tour_list();
        logger.debug("touristAttrVoList.size: {}", touristAttrVoList.size());
        for(TouristAttrVo touristAttrVo: touristAttrVoList) {
            logger.debug(">>>> touristAttrVo: {}", touristAttrVo);
        }

        return touristAttrVoList;
    }
    public int add_tour_list() {
        try {
            JsonLoader jsonLoader = new JsonLoader(objectMapper);
            List<TouristAttrVo> touristAttrVoList = jsonLoader.load_tourist_attr();

            for(TouristAttrVo touristAttrVo: touristAttrVoList) {
                TouristAttrVo new_tourist = new TouristAttrVo();

                new_tourist.setName(touristAttrVo.getName());
                new_tourist.setContent(touristAttrVo.getContent());
                new_tourist.setRoad_address(touristAttrVo.getRoad_address());
                new_tourist.setParcel_address(touristAttrVo.getParcel_address());
                new_tourist.setLat(touristAttrVo.getLat());
                new_tourist.setLng(touristAttrVo.getLng());
                new_tourist.setTel(touristAttrVo.getTel());
                new_tourist.setAssigned_date(touristAttrVo.getAssigned_date());

                adminMapper.add_tour_list(new_tourist);
            }
            return touristAttrVoList.size();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
