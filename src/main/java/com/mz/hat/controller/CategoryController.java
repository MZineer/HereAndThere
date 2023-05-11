package com.mz.hat.controller;

import com.mz.hat.service.CategoryService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import com.mz.hat.support.result.MspStatus;
import com.mz.hat.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@MSP
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<MspResult> list() {
        MspResult mspResult;
        List<CategoryVo> categoryVos = categoryService.list();

        int categoryVosSize = categoryVos.size();

        if(categoryVosSize > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, categoryVos);
        } else {
            mspResult = MspUtil.makeResult("8888", "등록된 카테고리가 없습니다.", null);
        }
        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }
}
