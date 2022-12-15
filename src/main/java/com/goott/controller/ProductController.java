package com.goott.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.goott.domain.BasketVO;
import com.goott.domain.PageReview;
import com.goott.domain.ProductBrandVO;
import com.goott.domain.ProductCategoryVO;
import com.goott.domain.ProductReviewVO;
import com.goott.domain.ProductVO;
import com.goott.service.ProductBrandService;
import com.goott.service.ProductCategoryService;
import com.goott.service.ProductService;
import com.goott.service.UserService;

import lombok.extern.log4j.Log4j;

@RequestMapping(value = "/product/*")
@Controller
@Log4j
public class ProductController {

    @Inject
    ProductBrandService productBrandService;
    @Inject
    ProductCategoryService productCategoryService;
    @Inject
    ProductService productService;
    @Inject
    UserService userService;

    /**
     * 상품 등록 페이지 요청
     *
     * @param model 상품 정보(브랜드 목록, 카테고리 목록)
     * @return 상품 등록 페이지
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Model model) {

        List<ProductBrandVO> brandList = productBrandService.getList(); // 현재 등록된 상품 브랜드 목록
        List<ProductCategoryVO> categoryList = productCategoryService.getList(); // 현재 등록된 상품 카테고리 목록
        model.addAttribute("brandList", brandList);
        model.addAttribute("categoryList", categoryList);

        return "/shop/product/product_add";
    }

    /**
     * 상품 등록 하기
     *
     * @param productVO 상품 정보
     * @param imgs1     상품 이미지 list
     * @param imgs2     상품 설명 이미지 list
     * @return 관리자 상품 목록 페이지
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(ProductVO productVO, @RequestParam(value = "imgs1") MultipartFile[] imgs1,
                               @RequestParam(value = "imgs2") MultipartFile[] imgs2) {

        // 코멘트 없으면 초기화
        if (productVO.getProduct_comment().trim().equals("") || productVO.getProduct_comment().trim() == null || productVO.getProduct_comment().trim().length() == 0)
            productVO.setProduct_comment("no comment");
        // 유튜브 주소 입력 안하면 초기화
        if (productVO.getProduct_youtube_url().trim().equals("") || productVO.getProduct_youtube_url().trim() == null || productVO.getProduct_youtube_url().trim().length() == 0)
            productVO.setProduct_youtube_url("no url");

        productService.saveProduct(productVO, imgs1, imgs2); // 상품 등록

        return "redirect:/shop/admin";
    }

    /**
     * 상품 수정 페이지 요청
     *
     * @param product_id 상품 번호
     * @param model      상품 정보(상품 브랜드 목록, 상품 카테고리 목록, 수정할 상품 정보)
     * @return 상품 수정 페이지
     */
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGet(@RequestParam String product_id, Model model) {

        ProductVO productVO = productService.getPrdocutDetail(Integer.parseInt(product_id));
        List<ProductBrandVO> brandList = productBrandService.getList();
        List<ProductCategoryVO> categoryList = productCategoryService.getList();
        model.addAttribute("brandList", brandList); // 현재 등록된 상품들의 브랜드 목록
        model.addAttribute("categoryList", categoryList); // 현재 등록된 상품들의 카테고리 목록
        model.addAttribute("product", productVO); // 수정할 상품 정보

        return "shop/product/product_modify";
    }

    /**
     * 상품 수정하기
     *
     * @param productVO 상품 정보
     * @param imgs1     상품 이미지 list
     * @param imgs2     상품 설명 이미지 list
     * @param model     상품 수정 처리 결과
     * @return 알림창
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyPost(ProductVO productVO, @RequestParam(value = "imgs1") MultipartFile[] imgs1,
                             @RequestParam(value = "imgs2") MultipartFile[] imgs2, Model model) {

        // 코멘트 없으면 초기화
        if (productVO.getProduct_comment().trim().equals("") || productVO.getProduct_comment().trim() == null
                || productVO.getProduct_comment().trim().length() == 0)
            productVO.setProduct_comment("no comment");
        // 유튜브 주소 입력 안하면 초기화
        if (productVO.getProduct_youtube_url().trim().equals("") || productVO.getProduct_youtube_url().trim() == null
                || productVO.getProduct_youtube_url().trim().length() == 0)
            productVO.setProduct_youtube_url("no url");


        int result = productService.updateProduct(productVO, imgs1, imgs2); // 상품 수정하기
        int product_id = productVO.getProduct_id(); // 수정한 상품 번호
        if (result == 1) {
            model.addAttribute("msg", "수정 되었습니다.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        } else {
            model.addAttribute("msg", "죄송합니다. 잠시후 다시 시도해 주세요.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        }
        return "common/alert";

    }

    /**
     * 상품 리뷰 목록(10개)
     *
     * @param param 상품 번호, 리뷰 목록 요청 페이지 번호
     * @param model 상품 리뷰 10개, 리뷰 게시판 페이지 정보 엔티티
     * @return 리뷰 출력 페이지
     */
    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String moreReview(@RequestBody Map<String, Object> param, Model model) {

        int product_id = Integer.parseInt(param.get("product_id").toString());
        int currentPage = Integer.parseInt(param.get("currentPage").toString());

        // 리뷰 전체 개수
        int totalNum = productService.getReviewTotalNum(product_id);
        // 리뷰 페이지 엔티티 초기화
        PageReview pageReview = new PageReview(currentPage, totalNum);
        // 상품 아이디
        pageReview.setProduct_id(product_id);

        // 상품 리뷰 목록
        List<ProductReviewVO> reviewList = productService.getProductReviewList(pageReview);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pageReview", pageReview);
        return "/shop/product/productReview";
    }

    /**
     * 상품 상세 정보 페이지 요청
     *
     * @param product_id  상품 번호
     * @param currentPage 상품 리뷰 페이지(초기값 1)
     * @param model       상품 정보, 상품 리뷰(10개), 리뷰 게시판 페이지 엔티티, 상품 후기(배송 속도), 상품 평점
     * @param request
     * @return 상품 상세 정보 페이지
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailGet(@RequestParam(value = "product_id") int product_id,
                            @RequestParam(defaultValue = "1") int currentPage, Model model, HttpServletRequest request) {


        HttpSession session = request.getSession();
        // 관리자 접근
        if (session.getAttribute("login_id") != null && session.getAttribute("login_auth").toString().equals("관리자")) {
            String temp_product_id = Integer.toString(product_id);
            return "redirect:/product/detail/admin?product_id=" + temp_product_id; // 관리자용 상품 상세 정보 페이지로 이동
        }

        // 상품 상세 정보
        ProductVO productVO = productService.getPrdocutDetail(product_id);
        // 작성된 리뷰 전체 개수
        int totalNum = productService.getReviewTotalNum(product_id);
        // 리뷰 페이지 엔티티 초기화
        PageReview pageReview = new PageReview(currentPage, totalNum);
        // 상품 후기(배송 속도)
        List<Map<String, Object>> data = productService.getSpeedAverage(product_id);
        // 상품 평점
        List<Map<String, Object>> gradeData = productService.getGradeAverage(product_id);
        // 상품 아이디
        pageReview.setProduct_id(product_id);
        // 상품 리뷰 목록(10개)
        List<ProductReviewVO> reviewList = productService.getProductReviewList(pageReview);

        model.addAttribute("product", productVO);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pageReview", pageReview);
        model.addAttribute("data", data);
        model.addAttribute("gradeData", gradeData);

        return "shop/product/product_detail_user";
    }

    /**
     * 관리자용 상품 상세 정보 페이지 요청
     *
     * @param product_id  상품 번호
     * @param currentPage 상품 리뷰 페이지(초기값 1)
     * @param model       상품 정보, 상품 리뷰(10개), 리뷰 게시판 페이지 엔티티, 상품 후기(배송 속도), 상품 평점
     * @return 상품 상세 정보 페이지
     */
    @RequestMapping(value = "/detail/admin", method = RequestMethod.GET)
    public String detailAdminGet(@RequestParam(value = "product_id") int product_id,
                                 @RequestParam(defaultValue = "1") int currentPage, Model model) {

        // 상품 상세
        ProductVO productVO = productService.getPrdocutDetail(product_id);
        // 작성된 리뷰 전체 개수
        int totalNum = productService.getReviewTotalNum(product_id);
        // 리뷰 페이지 엔티티 초기화
        PageReview pageReview = new PageReview(currentPage, totalNum);
        // 상품 후기(배송 속도)
        List<Map<String, Object>> data = productService.getSpeedAverage(product_id);
        // 상품 아이디
        pageReview.setProduct_id(product_id);
        // 상품 평점
        List<Map<String, Object>> gradeData = productService.getGradeAverage(product_id);
        // 리뷰 목록(10개)
        List<ProductReviewVO> reviewList = productService.getProductReviewList(pageReview);

        model.addAttribute("product", productVO);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pageReview", pageReview);
        model.addAttribute("data", data);
        model.addAttribute("gradeData", gradeData);

        return "shop/product/product_detail_admin";
    }

    /**
     * 상품 비공개 처리하기
     *
     * @param product_id 상품 번호
     * @param model      상품 비공개 처리 결과
     * @return 알림창
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam int product_id, Model model) {

        int result = productService.setProductClosed(product_id); // 상품 비공개 처리
        if (result == 1) {
            model.addAttribute("msg", "비공개 처리 되었습니다.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        } else {
            model.addAttribute("msg", "죄송합니다. 잠시후 다시 시도해 주세요.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        }
        return "/common/alert";
    }

    /**
     * 장바구니에 상품 담기
     *
     * @param basketVO 장바구니 정보
     * @return 상품 담기 결과
     */
    @ResponseBody
    @RequestMapping(value = "basket", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String basket(@RequestBody BasketVO basketVO) {

        String resultText = userService.setBasket(basketVO); // 현재 상품 장바구니에 저장하기
        return resultText;
    }
}
