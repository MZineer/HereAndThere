package com.mz.hat.controller;

import com.mz.hat.service.ReviewService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import com.mz.hat.support.result.MspStatus;
import com.mz.hat.vo.ReviewVo;
import com.mz.hat.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.Console;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
        List<ReviewVo> reviewVos = reviewService.list();

        int reviewVoSize = reviewVos.size();

        if(reviewVoSize > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, reviewVos);
        } else {
            mspResult = MspUtil.makeResult("8888", "등록된 게시글이 없습니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }

    @GetMapping("/write")
    public ModelAndView get_write() {
        return new ModelAndView("pages/review/write");
    }

    @PostMapping("/write")
    public ResponseEntity<MspResult> post_write(@RequestPart(value = "review") Map<String, String> map,
                                                @RequestPart(value = "file_name", required = false) List<MultipartFile> images,
                                                HttpSession session) throws IOException {
        MspResult mspResult;

        ReviewVo reviewVo = new ReviewVo();
        UserVo userVo = (UserVo) session.getAttribute("user");
        reviewVo.setTitle(map.get("title"));
        reviewVo.setTourist_attr_name(map.get("tourist_attr_name"));
        reviewVo.setUser_id(userVo.getId());
        reviewVo.setContent(map.get("content"));

        System.out.println(reviewVo);
        int affectRow = reviewService.write(reviewVo, images);

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
