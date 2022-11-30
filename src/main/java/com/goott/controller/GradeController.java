package com.goott.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import com.goott.service.GradeAdminService;
import com.goott.service.GradeService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class GradeController {

    @Inject
    GradeService gradeService;

    @Autowired
    GradeAdminService gradeAdminService;


    //유저 등급(내 정보) 확인
    @RequestMapping(value = "/grade")
    public ModelAndView grade(@RequestParam Map<String, Object> map) {
        ModelAndView mv = new ModelAndView();

        String member_id = map.get("member_id").toString();

        Map<String, Object> Info = gradeService.getUserGradeInfo(member_id);

        //해당 아이디의 등급 정보 가져와 모델에 저장
        mv.addObject("Info", Info);


        //다음 등급 정보 가져오기
        int next_grade_id = Integer.parseInt(Info.get("grade_id").toString()) + 1;
        //다음 등급 정보 가져오기 쿼리
        GradeVO nextInfo = gradeService.select(next_grade_id);
        mv.addObject("nextInfo", nextInfo);


        List<GradeVO> gradeList = gradeService.gradePolicyInfo();

        Map<String, Object> mav = new HashMap<>();
        mav.put("InfoAll", gradeList);


        mv.addObject("map", mav);

        //리턴 주소 저장
        mv.setViewName("/user/grade");


        return mv;
    }


    //관리자 - 등급 정책 리스트(gradePolicy_admin)
    @RequestMapping(value = "/gradePolicy_admin")
    public ModelAndView gradePolicy(ModelAndView mav) {

        int gradeCount = gradeAdminService.gradeCount();
        mav.setViewName("grade/gradePolicy_admin");
        mav.addObject("gradePolicy", gradeAdminService.gradePolicy());
        mav.addObject("gradeCount", gradeCount);
        return mav;
    }

    //관리자 - 등급 정책 삭제
    @RequestMapping(value = "/gradePolicy_adminDelete", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String gradePolicyDelete(@RequestParam(value = "valueArr[]") List<String> valueArr) {

        Iterator it = valueArr.iterator();
        String resultInfo = "삭제됐습니다.";
        while (it.hasNext()) {
            int temp = Integer.parseInt(it.next().toString());
            String result = gradeAdminService.gradePolicyDelete(temp);
            if (result.equals("에러났음")) {
                resultInfo = "삭제되지 않았습니다. 다시 확인해주세요.";
                break;
            }

        }
//			
        return resultInfo;
    }

    //관리자 - 등급 정책 추가
    @RequestMapping(value = "/gradePolicyAdd_admin", method = RequestMethod.GET)
    public String gradePolicyAdd(Model model) {


        int gradeCount = gradeAdminService.gradeCount() + 1;
        model.addAttribute("gradeCount", gradeCount);
        return "grade/gradePolicyAdd_admin";
    }

    //관리자 - 등급 정책 추가
    @RequestMapping(value = "/gradePolicyAdd_admin", method = RequestMethod.POST)
    public String gradePolicyAdd(@RequestParam MultipartFile img_url, GradeVO gradeVO, HttpServletRequest request) {

        String fileRealName = img_url.getOriginalFilename();
        //잘 받아오는지 확인 - 삭제 예정

        String fileExtension = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
        String uploadFolder = "C:\\gradeImg";
        UUID uuid = UUID.randomUUID();
        //어떻게 찍히는지 확인 - 삭제 예정

        String[] uuids = uuid.toString().split("-");
        String uniqueName = uuids[0];

        //체크용 - 삭제예쩡


        File saveFile = new File(uploadFolder + "\\" + uniqueName + fileExtension); //uuid 적용
        try {
            img_url.transferTo(saveFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gradeVO.setGrade_img_url(uniqueName + fileExtension);
        gradeAdminService.gradePolicyAdd(gradeVO);

        return "redirect:/gradePolicy_admin";
    }
}
