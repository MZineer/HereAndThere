package com.mz.hat.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
public class ReviewVo {
    private int id;
    private int tourist_attrs_id;
    private int user_id;
    private String content;
    private List<ImgVo> img;
    private int likes;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
}
