package com.goott.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DrawEnterVO {
    private int draw_enter_id; // 이벤트 응모 번호
    private int draw_id; // 이벤트 번호
    private Date draw_enter_regdate; // 이벤트 응모일
    private String member_id; // 회원 아이디
    private String draw_winning; // 당첨 여부
}
