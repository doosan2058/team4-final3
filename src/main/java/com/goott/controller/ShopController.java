package com.goott.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.goott.domain.ProductBrandVO;
import com.goott.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goott.domain.PageShop;
import com.goott.domain.ProductCategoryVO;
import com.goott.domain.ProductVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = {"/shop/*", "/shop"})

public class ShopController {

    @Inject
    ProductService productService;
    @Inject
    ProductCategoryService productCategoryService;
    @Inject
    ProductBrandService productBrandService;
    @Inject
    UserService userService;
    @Inject
    AdminService adminService;

    /**
     * 쇼핑몰 메인 페이지 요청
     *
     * @param model   상품 목록(10개), 현재 상품 페이지, 상품 카테고리 목록, 상품 브랜드 목록, 판매량 탑 10 목록, 로그인 유저 등급 정보,
     *                최근 등록된 광고 5, 최근 공지 5개
     * @param request
     * @return 쇼핑몰 메인 페이지
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String shopMainGet(Model model, HttpServletRequest request) {

        // 관리자면 관리자 전용 메인 페이지로 이동
        HttpSession session = request.getSession();
        if (session.getAttribute("login_auth") != null) {
            String auth = session.getAttribute("login_auth").toString();
            if (auth.equals("관리자")) { // 권환 체크
                return "redirect:/shop/admin";
            }
        }

        // 전체 페이지 수 조회
        int totalPage = (int) Math.ceil((productService.getPageTotalNum(0, 0) / (double) 10.0));
        PageShop pageShop = new PageShop(1, totalPage, "regist"); // 쇼핑몰 페이지 엔티티 초기화
        // 카테고리 목록 조회
        List<ProductCategoryVO> categoryList = productCategoryService.getList();
        // 브랜드 목록 조회
        List<ProductBrandVO> brandList = productBrandService.getList();
        // 판매 탑 10 목록
        List<Map<String, Object>> topProduct = productService.getProductTopSales();
        // 공개 상품 10개
        List<ProductVO> productList = productService.getProductList(pageShop);
        //유트브 최근 광고 5개
        List<ProductVO> youtubeList = productService.getYoutubeList();
        //공지사항 상위 5개
        List<Map<String, Object>> noticeList = adminService.getNoticeList();

        // 로그인 되어 있다면 회원 등급, 프로필 이미지 정보 전달
        Map<String, Object> userInfo = null;
        // 로그인 중이라면
        if (session.getAttribute("login_id") != null) {
            String member_id = session.getAttribute("login_id").toString();
            userInfo = userService.getUserProfileImgUrlAndGradeName(member_id);
        }

        model.addAttribute("productList", productList);
        model.addAttribute("pageShop", pageShop);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        model.addAttribute("topProduct", topProduct);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("youtubeList", youtubeList);
        model.addAttribute("noticeList", noticeList);

        return "shop/main/shop_user";
    }

    /**
     * 관리자용 쇼핑몰 메인 페이지 요청
     *
     * @param model   상품 목록(10개), 현재 상품 페이지, 상품 카테고리 목록, 상품 브랜드 목록, 판매량 탑 10 목록, 최근 등록된 광고 5,
     *                최근 공지 5개
     * @param request
     * @return 관리자용 쇼핑몰 메인 페이지
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String shopMainAdminGet(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();

        // 전체 페이지 수 조회
        int totalPage = (int) Math.ceil((productService.getPageTotalNumAll(0, 0) / (double) 10.0));
        PageShop pageShop = new PageShop(1, totalPage, "regist");
        // 카테고리 목록 조회
        List<ProductCategoryVO> categoryList = productCategoryService.getList();
        // 브랜드 목록 조회
        List<ProductBrandVO> brandList = productBrandService.getList();
        // 판매 탑 10 목록
        List<Map<String, Object>> topProduct = productService.getProductTopSales();
        //전체 상품 10개
        List<ProductVO> productList = productService.getProductListAll(pageShop);
        //유트브 최근 광고 5개
        List<ProductVO> youtubeList = productService.getYoutubeList();
        //공지사항 상위 5개
        List<Map<String, Object>> noticeList = adminService.getNoticeList();

        model.addAttribute("productList", productList);
        model.addAttribute("pageShop", pageShop);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        model.addAttribute("topProduct", topProduct);
        model.addAttribute("youtubeList", youtubeList);
        model.addAttribute("noticeList", noticeList);

        return "shop/main/shop_admin";
    }


    /**
     * 상품 10개 더보기
     *
     * @param pageShop 상품 페이지 엔티티
     * @param model    상품 목록(10개)
     * @return 상품 목록 출력 페이지
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String listAjaxPost(@RequestBody PageShop pageShop, Model model) {

        int category_id = pageShop.getCategory_id();
        int brand_id = pageShop.getBrand_id();

        // pageShop 초기화
        int totalPage = (int) Math.ceil((productService.getPageTotalNum(category_id, brand_id) / (double) 10.0));

        pageShop.setTotalPage(totalPage); // 조건에 맞는 상품 총 개수 초기화
        pageShop.setStartNum((pageShop.getCurrentPage() - 1) * 10); // 다음 검색 시작 번호 초기화
        pageShop.setEndNum(10);

        // 공개 상품 리스트
        List<ProductVO> productList = productService.getProductList(pageShop);

        model.addAttribute("productList", productList);
        model.addAttribute("pageShop", pageShop);

        return "shop/main/productDivUser";
    }

    /**
     * 관리자용 상품 10개 더보기
     *
     * @param pageShop 상품 페이지 엔티티
     * @param model    상품 목록(10개)
     * @return 상품 목록 출력 페이지
     */
    @RequestMapping(value = "/list/admin", method = RequestMethod.POST)
    public String listAjaxAdminPost(@RequestBody PageShop pageShop, Model model) {

        int category_id = pageShop.getCategory_id();
        int brand_id = pageShop.getBrand_id();

        // pageShop 초기화
        int totalPage = (int) Math.ceil((productService.getPageTotalNumAll(category_id, brand_id) / (double) 10.0));

        pageShop.setTotalPage(totalPage);
        pageShop.setStartNum((pageShop.getCurrentPage() - 1) * 10);
        pageShop.setEndNum(10);
        // 전체 상품 리스트
        List<ProductVO> productList = productService.getProductListAll(pageShop);

        model.addAttribute("productList", productList);
        model.addAttribute("pageShop", pageShop);
        return "shop/main/productDivAdmin";
    }

    /**
     * 팝업창 요청 페이지
     *
     * @param model 최근 등록된 공개 상품
     * @return 팝업창 페이지
     */
    @RequestMapping(value = "/popup", method = RequestMethod.GET)
    public String popUp(Model model) {
        ProductVO productVO = productService.getNewestProduct();
        model.addAttribute("item", productVO); // 최근 등록된 공개 상품 엔티티
        return "shop/main/popup";
    }

}
