package com.mz.hat.controller;

import com.mz.hat.service.ImageService;
import com.mz.hat.service.TouristAttrService;
import com.mz.hat.support.annotation.MSP;
import com.mz.hat.vo.ImageVo;
import com.mz.hat.vo.TouristAttrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@MSP
@Slf4j
@RequestMapping("/tourist")
@RestController
public class TouristAttrController {

    @Autowired
    private TouristAttrService touristAttrService;

    @Autowired
    private ImageService imageService;

    @Value("${google.secret.map.key}")
    private String apiKey;

    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        TouristAttrVo touristAttrVo = touristAttrService.detail(Integer.parseInt(id));
        List<ImageVo> imageVoList = imageService.get_image("tourist_attrs", touristAttrVo.getId());
        model.addAttribute("images", imageVoList);
        model.addAttribute("tourist", touristAttrVo);
        model.addAttribute("apiKey", apiKey);

        return new ModelAndView("pages/tourist/detail");
    }
}
