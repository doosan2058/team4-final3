package com.goott.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DrawEnterVO {
    int draw_enter_id;
    int draw_id;
    Date draw_enter_regdate;
    String member_id;
    String draw_winning;
}
