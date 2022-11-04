package com.goott.controller;

import com.goott.domain.DrawEnterVO;
import com.goott.domain.DrawVO;
import com.goott.service.DrawService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Log4j
@Controller
public class DrawController {

    @Inject
    DrawService drawService;

    //회원 이벤트 등록 페이지
    @RequestMapping(value = "/draw/draw_customer",method = RequestMethod.GET)
    public String draw(Model model){
        List<Map<String, Object>> returnMap = drawService.getAllDrawList();
        model.addAttribute("draw", returnMap);
        return "/shop/draw/draw_customer";
    }

    //회원 이벤트 응모 비동기
    @ResponseBody
    @RequestMapping(value = "/draw/applicationDraw", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String applicationDraw(@RequestBody Map<String, Object> param){
        // DrawEnterVO 초기화
        DrawEnterVO drawEnterVO = new DrawEnterVO();
        drawEnterVO.setDraw_id(Integer.parseInt( param.get("draw_id").toString() ));
        drawEnterVO.setMember_id( param.get("member_id").toString());
        // 이벤트 응모
        String resultText = drawService.registerDraw(drawEnterVO);

        return resultText;
    }

    // 이벤트 응모자 목록 보기
    @ResponseBody
    @RequestMapping(value = "/draw/drawEnterList", method = RequestMethod.POST)
    public List<DrawEnterVO> getDrawEnterList(@RequestBody Map<String, Object> param){
        int draw_id = Integer.parseInt( param.get("draw_id").toString() );
        List<DrawEnterVO> enterList = drawService.getDrawEnterList(draw_id);

        return enterList;
    }

    //이벤트 인원 정보
    @ResponseBody
    @RequestMapping(value = "/draw/drawEnterInfo", method = RequestMethod.POST)
    public Map<String, Object> getDrawEnterInfo(@RequestBody Map<String, Object> drawCountInfo){
        int draw_id = Integer.parseInt( drawCountInfo.get("draw_id").toString() );
        Map<String, Object> returnMap = drawService.getDrawEnterInfo(draw_id);

        return returnMap;
    }

    // 관리자 이벤트 관리 페이지
    @RequestMapping(value = "/draw/draw_admin", method = RequestMethod.GET)
    public String drawAdmin(Model model){
        List<Map<String, Object>> returnMap = drawService.getAllDrawList();
        model.addAttribute("draw", returnMap);
        return "/shop/draw/draw_admin";
    }

    // 유저 이벤트 당첨
    @ResponseBody
    @RequestMapping(value = "/draw/userEnter", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String userEnter(@RequestBody Map<String, Object> param){
        drawService.userEnter(param);
        return "당첨 처리 하였습니다.";
    }

    // 이벤트 수정
    @RequestMapping(value = "/draw/modify", method = RequestMethod.GET)
    public String drawModify(Model model, @RequestParam int draw_id){
        Map<String, Object> item = drawService.getDraw(draw_id);
        model.addAttribute("item", item);
        return "/shop/draw/draw_modify";
    }

    // 이벤트 수정 post
    @RequestMapping(value = "/draw/modify", method = RequestMethod.POST)
    public String drawModifyPost(DrawVO drawVO, Model model){
        log.info(drawVO);
        drawService.modifyDraw(drawVO);
        model.addAttribute("msg", "이벤트를 수정 하였습니다.");
        model.addAttribute("url", "/draw/draw_admin");
        return "/common/alert";
    }

    // 새로운 이벤트 등록
    @RequestMapping(value = "/draw/add", method = RequestMethod.GET)
    public String drawAdd(){
        return "/shop/draw/draw_add";
    }

}
