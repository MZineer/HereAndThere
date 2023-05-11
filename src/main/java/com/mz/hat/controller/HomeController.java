package com.mz.hat.controller;

import com.mz.hat.service.BoardService;
import com.mz.hat.service.ReviewService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@MSP
@Slf4j
@RestController
public class HomeController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private ReviewService reviewService;

    @Value("${google.secret.map.key}")
    private String apiKey;

    @GetMapping("/")
    public ModelAndView home(Model model) {
        model.addAttribute("apiKey", apiKey);
        return new ModelAndView("index");
    }

    @GetMapping("/get/reviews")
    public ResponseEntity<MspResult> get_reviews() {

        return null;
    }

    @GetMapping("/get/posts")
    public ResponseEntity<MspResult> get_posts() {

        return null;
    }

    @GetMapping("/error")
    public ResponseEntity<MspResult> error() {
        MspResult mspResult = MspUtil.makeResult("4444", "세션이 존재하지 않습니다.", null);

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }
}
