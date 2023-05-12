package com.mz.hat.controller;

import com.mz.hat.service.ReviewService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import com.mz.hat.support.result.MspStatus;
import com.mz.hat.vo.ReviewVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@MSP
@Slf4j
@RequestMapping("/review")
@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("pages/review/list");
    }

    @GetMapping("/list/get")
    public ResponseEntity<MspResult> get_list() {
        MspResult mspResult;
        List<ReviewVo> postVos = reviewService.list();

        int postVosSize = postVos.size();

        if(postVosSize > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, postVos);
        } else {
            mspResult = MspUtil.makeResult("8888", "등록된 게시글이 없습니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<MspResult> post_write(@RequestBody ReviewVo reviewVo) {
        MspResult mspResult;

        int affectRow = reviewService.write(reviewVo);

        if(affectRow > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, reviewVo);
        } else {
            mspResult = MspUtil.makeResult("9999", "중복된 글입니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MspResult> post_modify(@PathVariable("id") String id ,@RequestBody ReviewVo reviewVo) {
        MspResult mspResult;

        reviewVo.setId(Integer.parseInt(id));
        int affectRow = reviewService.modify(reviewVo);
        if(affectRow > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, reviewVo);
        } else {
            mspResult = MspUtil.makeResult("4444", "존재하지 않는 글입니다.", reviewVo);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MspResult> del_delete(@PathVariable("id") String id) {
        MspResult mspResult;

        ReviewVo vo = new ReviewVo();
        vo.setId(Integer.parseInt(id));
        int affectRow = reviewService.delete(vo);

        if(affectRow > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, vo);
        } else {
            mspResult = MspUtil.makeResult("4444", "존재하지 않는 글입니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }
}
