package com.goott.controller;

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

    // 상품 등록
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Model model) {


        List<ProductBrandVO> brandList = productBrandService.getList();
        List<ProductCategoryVO> categoryList = productCategoryService.getList();
        model.addAttribute("brandList", brandList);
        model.addAttribute("categoryList", categoryList);

        return "shop/product/product_add";
    }

    // 상품 등록
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(ProductVO productVO, @RequestParam(value = "imgs1") MultipartFile[] imgs1,
                               @RequestParam(value = "imgs2") MultipartFile[] imgs2) {

        // 코멘트 없으면 초기화
        if (productVO.getProduct_comment().trim().equals("") || productVO.getProduct_comment().trim() == null
                || productVO.getProduct_comment().trim().length() == 0)
            productVO.setProduct_comment("no comment");
        // 유튜브 주소 입력 안하면 초기화
        if (productVO.getProduct_youtube_url().trim().equals("") || productVO.getProduct_youtube_url().trim() == null
                || productVO.getProduct_youtube_url().trim().length() == 0)
            productVO.setProduct_youtube_url("no url");


        productService.saveProduct(productVO, imgs1, imgs2);

        return "redirect:/shop/admin";
    }

    // 상품 수정
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyGet(@RequestParam String product_id, Model model) {

        ProductVO productVO = productService.getPrdocutDetail(Integer.parseInt(product_id));
        List<ProductBrandVO> brandList = productBrandService.getList();
        List<ProductCategoryVO> categoryList = productCategoryService.getList();
        model.addAttribute("brandList", brandList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("product", productVO);


        return "shop/product/product_modify";
    }

    // 상품 수정
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


        int result = productService.updateProduct(productVO, imgs1, imgs2);
        int product_id = productVO.getProduct_id();
        if (result == 1) {
            model.addAttribute("msg", "수정 되었습니다.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        } else {
            model.addAttribute("msg", "죄송합니다. 잠시후 다시 시도해 주세요.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        }
        return "common/alert";

    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String moreReview(@RequestBody Map<String, Object> param, Model model) {

        int product_id = Integer.parseInt(param.get("product_id").toString());
        int currentPage = Integer.parseInt(param.get("currentPage").toString());

        // 리뷰 페이지
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

    // 상품 상세
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detailGet(@RequestParam(value = "product_id") int product_id,
                            @RequestParam(defaultValue = "1") int currentPage, Model model, HttpServletRequest request) {


        HttpSession session = request.getSession();
        if (session.getAttribute("login_id") != null && session.getAttribute("login_auth").toString().equals("관리자")) {
            String temp_product_id = Integer.toString(product_id);
            return "redirect:/product/detail/admin?product_id=" + temp_product_id;
        }


        // 상품 상세 정보
        ProductVO productVO = productService.getPrdocutDetail(product_id);

        // 리뷰 페이지
        int totalNum = productService.getReviewTotalNum(product_id);
        // 리뷰 페이지 엔티티 초기화
        PageReview pageReview = new PageReview(currentPage, totalNum);
        // 상품 후기 배송 정보
        List<Map<String, Object>> data = productService.getSpeedAverage(product_id);
        // 상품 평점
        List<Map<String, Object>> gradeData = productService.getGradeAverage(product_id);

        // 상품 아이디
        pageReview.setProduct_id(product_id);


        // 상품 리뷰 목록
        List<ProductReviewVO> reviewList = productService.getProductReviewList(pageReview);
        model.addAttribute("product", productVO);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pageReview", pageReview);
        model.addAttribute("data", data);
        model.addAttribute("gradeData", gradeData);

        return "shop/product/product_detail_user";
    }

    // 상품 상세 관리자
    @RequestMapping(value = "/detail/admin", method = RequestMethod.GET)
    public String detailAdminGet(@RequestParam(value = "product_id") int product_id,
                                 @RequestParam(defaultValue = "1") int currentPage, Model model) {

        // 상품 상세
        ProductVO productVO = productService.getPrdocutDetail(product_id);

        // 리뷰 페이지
        int totalNum = productService.getReviewTotalNum(product_id);
        // 리뷰 페이지 엔티티 초기화
        PageReview pageReview = new PageReview(currentPage, totalNum);

        // 상품 후기 배송 정보
        List<Map<String, Object>> data = productService.getSpeedAverage(product_id);
        // 상품 아이디
        pageReview.setProduct_id(product_id);

        // 상품 평점
        List<Map<String, Object>> gradeData = productService.getGradeAverage(product_id);
        // 리뷰 목록
        List<ProductReviewVO> reviewList = productService.getProductReviewList(pageReview);
        model.addAttribute("product", productVO);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("pageReview", pageReview);
        model.addAttribute("data", data);
        model.addAttribute("gradeData", gradeData);
        return "shop/product/product_detail_admin";
    }

    //상품 비공개 처리

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(@RequestParam int product_id, Model model) {

        int result = productService.setProductClosed(product_id);
        if (result == 1) {
            model.addAttribute("msg", "비공개 처리 되었습니다.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        } else {
            model.addAttribute("msg", "죄송합니다. 잠시후 다시 시도해 주세요.");
            model.addAttribute("url", "/product/detail/admin?product_id=" + Integer.toString(product_id));
        }
        return "common/alert";
    }

    @ResponseBody
    @RequestMapping(value = "basket", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
    public String basket(@RequestBody BasketVO basketVO) {

        String resultText = userService.setBasket(basketVO);
        return resultText;
    }
}
