package com.goott.controller;

import com.goott.domain.*;
import com.goott.mapper.ProductMapper;
import com.goott.service.DrawService;
import com.goott.service.ProductBrandService;
import com.goott.service.ProductCategoryService;
import com.goott.service.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j
@Controller
public class DrawController {

    @Inject
    DrawService drawService;
    @Inject
    ProductCategoryService productCategoryService;
    @Inject
    ProductBrandService productBrandService;
    @Inject
    ProductService productService;

    /**
     * 이벤트 페이지 요청
     *
     * @param model
     * @param request
     * @return 이벤트 페이지
     */
    @RequestMapping(value = "/draw/draw_customer", method = RequestMethod.GET)
    public String draw(Model model, HttpServletRequest request) {

        // 관리자 접근
        if (request.getSession().getAttribute("login_auth") != null) {
            // 세션에 저장된 권한이 관리자면 관리자용 이벤트 목록 페이지 이동
            if (request.getSession().getAttribute("login_auth").toString().equals("관리자"))
                return "redirect:/draw/draw_admin";
        }
        // 일반 방문자 접근 or 회원 접근
        List<Map<String, Object>> returnMap = drawService.getAllDrawList(); // 이벤트 정보 목록
        model.addAttribute("draw", returnMap);
        return "/shop/draw/draw_customer";
    }

    /**
     * 이벤트 응모
     *
     * @param param 응모 이벤트 번호, 응모 회원 아이디
     * @return 응모 결과 text
     */
    @ResponseBody
    @RequestMapping(value = "/draw/applicationDraw", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String applicationDraw(@RequestBody Map<String, Object> param) {

        DrawEnterVO drawEnterVO = new DrawEnterVO();
        drawEnterVO.setDraw_id(Integer.parseInt(param.get("draw_id").toString()));
        drawEnterVO.setMember_id(param.get("member_id").toString());
        String resultText = drawService.registerDraw(drawEnterVO); // 이벤트 응모

        return resultText;
    }

    /**
     * 이벤트 응모자 정보 목록
     *
     * @param param 정보 열람할 이벤트 번호
     * @return 응모자 정보 list
     */
    @ResponseBody
    @RequestMapping(value = "/draw/drawEnterList", method = RequestMethod.POST)
    public List<DrawEnterVO> getDrawEnterList(@RequestBody Map<String, Object> param) {

        int draw_id = Integer.parseInt(param.get("draw_id").toString());
        List<DrawEnterVO> enterList = drawService.getDrawEnterList(draw_id);
        return enterList;
    }

    /**
     * 해당 이벤트 정보 가져오기
     *
     * @param drawCountInfo 이벤트 번호
     * @return 이벤트 정보 (이벤트 모집인원, 현재 총 응모인원, 현재 총 당첨인원)
     */
    @ResponseBody
    @RequestMapping(value = "/draw/drawEnterInfo", method = RequestMethod.POST)
    public Map<String, Object> getDrawEnterInfo(@RequestBody Map<String, Object> drawCountInfo) {
        int draw_id = Integer.parseInt(drawCountInfo.get("draw_id").toString());
        Map<String, Object> returnMap = drawService.getDrawEnterInfo(draw_id);

        return returnMap;
    }

    /**
     * 관리자용 이벤트 관리 페이지 요청
     *
     * @param model
     * @return 관리자용 이벤트 관리 페이지
     */
    @RequestMapping(value = "/draw/draw_admin", method = RequestMethod.GET)
    public String drawAdmin(Model model) {
        List<Map<String, Object>> returnMap = drawService.getAllDrawList();
        model.addAttribute("draw", returnMap);
        return "/shop/draw/draw_admin";
    }

    /**
     * 응모 회원 이벤트 당첨 처리
     *
     * @param param 유저 아이디, 이벤트 번호
     * @return 당첨 처리 결과 text
     */
    @ResponseBody
    @RequestMapping(value = "/draw/userEnter", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String userEnter(@RequestBody Map<String, Object> param) {
        drawService.userEnter(param);
        return "해당 회원을 당첨 처리 하였습니다.";
    }

    /**
     * 이벤트 수정 페이지 요청
     *
     * @param model
     * @param draw_id 수정할 이벤트 번호
     * @return 이벤트 수정 페이지
     */
    @RequestMapping(value = "/draw/modify", method = RequestMethod.GET)
    public String drawModify(Model model, @RequestParam int draw_id) {
        Map<String, Object> item = drawService.getDraw(draw_id);
        model.addAttribute("item", item);
        return "/shop/draw/draw_modify";
    }

    /**
     * 이벤트 수정
     *
     * @param drawVO 이벤트 정보
     * @param model  수정 결과
     * @return 알림 페이지
     */
    @RequestMapping(value = "/draw/modify", method = RequestMethod.POST)
    public String drawModifyPost(DrawVO drawVO, Model model) {
        drawService.modifyDraw(drawVO);
        model.addAttribute("msg", "이벤트를 수정 하였습니다.");
        model.addAttribute("url", "/draw/draw_admin");
        return "/common/alert";
    }

    /**
     * 이벤트 등록 페이지 요청
     *
     * @param model 현재 등록된 상품들의 카테고리, 브랜드 목록
     * @return 이벤트 등록 페이지
     */
    @RequestMapping(value = "/draw/add", method = RequestMethod.GET)
    public String drawAdd(Model model) {
        List<ProductCategoryVO> categoryList = productCategoryService.getList();
        List<ProductBrandVO> brandList = productBrandService.getList();

        model.addAttribute("categoryList", categoryList); // 상품 카테고리
        model.addAttribute("brandList", brandList); // 상품 브랜드
        return "/shop/draw/draw_add";
    }

    /**
     * 검색 조건에 맞는 상품 검색
     *
     * @param param 상품 카테고리 번호, 상품 브랜드 번호
     * @return 상품 list
     */
    @ResponseBody
    @RequestMapping(value = "/draw/search", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public List<ProductVO> searchProductList(@RequestBody Map<String, Object> param) {
        int product_category_id = Integer.parseInt(param.get("product_category_id").toString());
        int product_brand_id = Integer.parseInt(param.get("product_brand_id").toString());
        // 검색 조건에 맞는 상품 list
        List<ProductVO> productList = productService.getDrawLimitedProductList(product_category_id, product_brand_id);

        return productList;
    }

    /**
     * 신규 이벤트 등록
     *
     * @param drawVO 이벤트 정보
     * @param model  이벤트 등록 결과
     * @return 알림 페이지
     */
    @RequestMapping(value = "/draw/add", method = RequestMethod.POST)
    public String drawAddPost(DrawVO drawVO, Model model) {

        drawService.addDraw(drawVO);
        model.addAttribute("msg", "이벤트가 등록되었습니다.");
        model.addAttribute("url", "/draw/draw_admin");

        return "/common/alert";
    }

}
