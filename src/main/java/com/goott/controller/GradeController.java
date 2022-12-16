package com.goott.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.goott.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.goott.domain.GradeVO;
import com.goott.service.GradeService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class GradeController {

    @Inject
    GradeService gradeService;

    @Inject
    S3Service s3Service;


    /**
     * 로그인 유저 등급 정보
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/grade")
    public ModelAndView grade(@RequestParam Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();
        String member_id = map.get("member_id").toString();
        Map<String, Object> info = gradeService.getUserGradeInfo(member_id); // 로그인 유저의 등급 정보
        List<GradeVO> gradeList = gradeService.gradePolicyInfo(); // 모든 등급 정보

        mv.addObject("info", info);
        mv.addObject("gradeList", gradeList);
        mv.setViewName("/user/grade");

        return mv;
    }


    /**
     * 관리자 등급 관리 페이지 요청
     *
     * @param mav 등급 정보, 총 등급 개수
     * @return 등급 관리 페이지
     */
    @RequestMapping(value = "/gradePolicy_admin")
    public ModelAndView gradePolicy(ModelAndView mav) {

        int gradeCount = gradeService.gradeCount();
        mav.setViewName("/grade/gradePolicy_admin");
        mav.addObject("gradePolicy", gradeService.gradePolicyInfo()); // 등급 정보 list
        mav.addObject("gradeCount", gradeCount); // 총 등급 개수
        return mav;
    }


    /**
     * 등급 추가 페이지 요청
     *
     * @param model 현재 총 등급 개수
     * @return 등급 추가 페이지
     */
    @RequestMapping(value = "/gradePolicyAdd_admin", method = RequestMethod.GET)
    public String gradePolicyAdd(Model model) {

        int gradeCount = gradeService.gradeCount(); // 현재 총 등급 개수
        int lastGradeEndPoint = gradeService.getLastGradeEndPoint(); // 마지막 등급 엔드 포인트
        model.addAttribute("gradeCount", gradeCount);
        model.addAttribute("lastGradeEndPoint", lastGradeEndPoint);
        model.addAttribute("LastDiscountAndAccrualPoint", gradeService.getLastDiscountAndAccrualPoint()); // 마지막 등급 할인율, 적립율
        return "/grade/gradePolicyAdd_admin";
    }

    /**
     * 등급 추가
     *
     * @param img_url 등급 이미지
     * @param gradeVO
     * @return
     */
    @RequestMapping(value = "/gradePolicyAdd_admin", method = RequestMethod.POST)
    public String gradePolicyAdd(@RequestParam MultipartFile img_url, GradeVO gradeVO) {

        String imgUrl = s3Service.uploadS3Img(img_url, "grade"); // 등급 이미지 AWS S3 에 업로드
        gradeVO.setGrade_img_url(imgUrl);
        gradeService.gradePolicyAdd(gradeVO); // 새로운 등급 정보 DB에 저장

        return "redirect:/gradePolicy_admin";
    }
}
