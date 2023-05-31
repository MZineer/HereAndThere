package com.mz.hat.service;

import com.mz.hat.dao.TouristAttrMapper;
import com.mz.hat.vo.TouristAttrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TouristAttrService {
    @Autowired
    private TouristAttrMapper touristAttrMapper;

    public TouristAttrVo search_name(String name) {
        return touristAttrMapper.search_name(name);
    }

    public TouristAttrVo detail(int id) {
        return touristAttrMapper.detail(id);
    }
}
