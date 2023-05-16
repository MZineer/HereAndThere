package com.mz.hat.vo;

import java.time.LocalDateTime;
import java.util.List;

public class TogetherVo {
    private int id;
    private String user_id;
    private String title;
    private String content;
    private List<ImgVo> img;
    private LocalDateTime departure_date;
    private LocalDateTime arrival_date;
    private LocalDateTime reg_date;
    private LocalDateTime mod_date;
}
