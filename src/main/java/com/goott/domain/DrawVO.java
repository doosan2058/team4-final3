package com.goott.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DrawVO {
    private int draw_id; // 이벤트 번호
    private String draw_title; // 이벤트 제목
    private int product_id; // 상품 번호
    private Date draw_regdate; // 이벤트 등록일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date draw_event_start_date; // 응모 시작일
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date draw_event_end_date; // 응모 종료일
    private String draw_comment; // 이벤트 내용
    private int draw_reqruit; // 응모 모집 인원
    private String draw_close; // 응모 마감 여부

}
