package com.mz.hat.controller;

import com.mz.hat.service.AdminService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import com.mz.hat.support.result.MspStatus;
import com.mz.hat.vo.TouristAttrVo;
import com.mz.hat.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@MSP
@RequestMapping("/admin")
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ModelAndView get_admin() {
        return new ModelAndView("admin/dashboard");
    }

    @GetMapping("/user_list")
    public ModelAndView user_list() {
        return new ModelAndView("admin/user_list");
    }

    @GetMapping("/user_list/get")
    public ResponseEntity<MspResult> get_user_list() {
        MspResult result;

        List<UserVo> userVos = adminService.get_user_list();

        int affectRow = userVos.size();

        if(affectRow > 0) {
            result = MspUtil.makeResult(MspStatus.OK, userVos);
        } else {
            result = MspUtil.makeResult("4444", "등록된 데이터가 없습니다.", null);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/tour_list")
    public ModelAndView tour_list() {
        return new ModelAndView("admin/tour_list");
    }

    @GetMapping("/tour_list/get")
    public ResponseEntity<MspResult> get_tour_list() {
        MspResult result;

        List<TouristAttrVo> touristAttrVoList = adminService.get_tour_list();

        int affectRow = touristAttrVoList.size();

        if(affectRow > 0) {
            result = MspUtil.makeResult(MspStatus.OK, touristAttrVoList);
        } else {
            result = MspUtil.makeResult("4444", "데이터 오류", null);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/tour_list/add")
    public ResponseEntity<MspResult> add_tour_list() {
        MspResult result;
        int affectRow = adminService.add_tour_list();

        if(affectRow > 0) {
            result = MspUtil.makeResult(MspStatus.OK, null);
        } else {
            result = MspUtil.makeResult("4444", "데이터 오류", null);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
