package com.mz.hat.controller;

import com.mz.hat.service.RouteService;
import com.mz.hat.service.TouristAttrService;
import com.mz.hat.support.MspUtil;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.support.result.MspResult;
import com.mz.hat.support.result.MspStatus;
import com.mz.hat.vo.RouteVo;
import com.mz.hat.vo.TogetherVo;
import com.mz.hat.vo.TouristAttrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MSP
@Slf4j
@RequestMapping("/route")
@RestController
public class RouteController {
    @Autowired
    private RouteService routeService;

    @Autowired
    private TouristAttrService touristAttrService;

    @GetMapping("/cord/get")
    public ResponseEntity<MspResult> get_cord(@RequestParam("route") String route) {
        MspResult mspResult;

        List<String> route_list = Arrays.asList(route.split("\\s+"));
        List<TouristAttrVo> routes = new ArrayList<>();
        for(int i = 0; i < route_list.size(); i++) {
            TouristAttrVo touristAttrVo = touristAttrService.search_name(route_list.get(i));
            routes.add(touristAttrVo);
        }

        int touristAttrVoSize = routes.size();

        if (touristAttrVoSize > 0) {
            mspResult = MspUtil.makeResult(MspStatus.OK, routes);
        } else {
            mspResult = MspUtil.makeResult("8888", "등록된 정보가 없습니다.", null);
        }

        return new ResponseEntity<>(mspResult, HttpStatus.OK);
    }
}
