package com.goott.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DrawVO {
    int draw_id;
    String draw_title;
    int product_id;
    Date draw_regdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date draw_event_start_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date draw_event_end_date;
    String draw_comment;
    int draw_reqruit;
    String draw_close;

}
