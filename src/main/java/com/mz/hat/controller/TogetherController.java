package com.mz.hat.controller;

import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@MSP
@Slf4j
@RequestMapping("/together")
@RestController
public class TogetherController {
    @GetMapping("/list")
    public ModelAndView list() {
        return new ModelAndView("pages/together/list");
    }

    @GetMapping("/list/get")
    public ResponseEntity<MspResult> get_list() {
        return null;
    }
}
