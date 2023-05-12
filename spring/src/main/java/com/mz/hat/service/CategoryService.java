package com.mz.hat.service;

import com.mz.hat.dao.CategoryMapper;
import com.mz.hat.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryVo> list() {
        List<CategoryVo> categoryVos = categoryMapper.list();
        logger.debug("postVos.size: {}", categoryVos.size());
        for(CategoryVo categoryVo: categoryVos) {
            logger.debug(">>>> post: {}", categoryVo);
        }

        return categoryVos;
    }
}
