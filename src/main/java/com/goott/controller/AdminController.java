package com.goott.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goott.domain.MemberVO;
import com.goott.service.AdminService;
import com.goott.service.SessionService;

import lombok.extern.log4j.Log4j;

@RequestMapping(value = {"/admin/*", "/admin"})
@Log4j
@Controller
public class AdminController {

    @Inject
    AdminService adminService;
    @Inject
    SessionService sessionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String adminGet() {
        return "/admin/admin";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<MemberVO> list = adminService.getMemberAllList();

        model.addAttribute("list", list);

        return "/admin/list";
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(@RequestParam String member_id, Model model) {


        MemberVO member = adminService.getMemberInfo(member_id);
        List<Map<String, Object>> freeMapList = adminService.getFreeList(member_id);
        List<Map<String, Object>> campingMapList = adminService.getCampingList(member_id);
        List<Map<String, Object>> qnaMapList = adminService.getQnaList(member_id);
        List<Map<String, Object>> orderMapList = adminService.getOrderList(member_id);

        model.addAttribute("member", member);
        model.addAttribute("freeMapList", freeMapList);
        model.addAttribute("campingMapList", campingMapList);
        model.addAttribute("qnaMapList", qnaMapList);
        model.addAttribute("orderMapList", orderMapList);

        log.info(qnaMapList);
        return "/admin/detail";
    }

    @RequestMapping(value = "send", method = RequestMethod.GET)
    public String sendGet() {
//		log.info("프로모션 메일 보내기 get ==============================================");

        return "/admin/send";
    }

    @RequestMapping(value = "send", method = RequestMethod.POST)
    public String send(@RequestParam String subject, @RequestParam String content) {
//		log.info("프로모션 메일 보내기 post ==============================================");
        adminService.setPromotionEmail(subject, content);
        return "/admin/admin";
    }


    @RequestMapping(value = "count", method = RequestMethod.GET)
    public String count(Model model) {
		log.info("방문자수 ===============================================");

        Map<String, Object> countMap = sessionService.countBasic();
        List<Map<String, Object>> countTotalDayMap = sessionService.countTotalDay();
        List<Map<String, Object>> countTotalWeekMap = sessionService.countTotalWeek();
        List<Map<String, Object>> countTotalDayMapBoard = sessionService.countTotalDayBoard();
        List<Map<String, Object>> countTotalWeekMapBoard = sessionService.countTotalWeekBoard();
        List<Map<String, Object>> countTotalDayMapQna = sessionService.countTotalDayQna();
        List<Map<String, Object>> countTotalWeekMapQna = sessionService.countTotalWeekQna();
        List<Map<String, Object>> countTotalDayMapShop = sessionService.countTotalDayShop();
        List<Map<String, Object>> countTotalWeekMapShop = sessionService.countTotalWeekShop();


        model.addAttribute("countMap", countMap);
        model.addAttribute("countTotalDayMap", countTotalDayMap);
        model.addAttribute("countTotalWeekMap", countTotalWeekMap);
        model.addAttribute("countTotalDayMapBoard", countTotalDayMapBoard);
        model.addAttribute("countTotalWeekMapBoard", countTotalWeekMapBoard);
        model.addAttribute("countTotalDayMapQna", countTotalDayMapQna);
        model.addAttribute("countTotalWeekMapQna", countTotalWeekMapQna);
        model.addAttribute("countTotalDayMapShop", countTotalDayMapShop);
        model.addAttribute("countTotalWeekMapShop", countTotalWeekMapShop);


        return "/admin/count";
    }
}
