package com.goott.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.goott.domain.ProductBrandVO;
import com.goott.service.ProductBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goott.domain.PageShop;
import com.goott.domain.ProductCategoryVO;
import com.goott.domain.ProductVO;
import com.goott.service.ProductCategoryService;
import com.goott.service.ProductService;
import com.goott.service.UserService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping(value = { "/shop/*", "/shop" })

public class ShopController {

	@Inject
	ProductService productService;
	@Inject
	ProductCategoryService productCategoryService;
	@Inject
	ProductBrandService productBrandService;
	@Inject
	UserService userService;

	// 쇼핑몰 메인
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String shopMainGet(Model model, HttpServletRequest request) {

		// 관리자면 관리자 전용 메인 페이지로 이동
		HttpSession session = request.getSession();
		if (session.getAttribute("login_auth") != null) {
			String auth = session.getAttribute("login_auth").toString();
			if (auth.equals("관리자")) {
				return "redirect:/shop/admin";
			}
		}
		
		//로그인 했을시 광고창
		//구매 이력 불러와서 제일 많이 구매한 상품 카테고리 알아내기
		//해당 카테고리 인기사품 노출

		// 전체 페이지 수 조회
		int totalPage = (int) Math.ceil((productService.getPageTotalNum(0, 0) / (double) 10.0));
		PageShop pageShop = new PageShop(1, totalPage);
		List<ProductVO> productList = productService.getProductList(pageShop);

		// 카테고리 목록 조회
		List<ProductCategoryVO> categoryList = productCategoryService.getList();

		// 브랜드 목록 조회
		List<ProductBrandVO> brandList = productBrandService.getList();

		// 판매 탑 10 목록
		List<Map<String, Object>> topProduct = productService.getProductTopSales();

		// 로그인 되어 있다면 회원 등급, 프로필 이미지 정보
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
		return "shop/main/shop_user";
	}

	// 쇼핑몰 메인 관리자
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String shopMainAdminGet(Model model, HttpServletRequest request) {
//		log.info("shop 관리자 메인 페이지 -----------------------------------------");

		HttpSession session = request.getSession();

		// 전체 페이지 수 조회
		int totalPage = (int) Math.ceil((productService.getPageTotalNumAll(0) / (double) 10.0));
		// 카테고리 목록 조회
		List<ProductCategoryVO> categoryList = productCategoryService.getList();
		PageShop pageShop = new PageShop(1, totalPage);
		List<ProductVO> productList = productService.getProductListAll(pageShop);

		// 판매 탑 10 목록
		List<Map<String, Object>> topProduct = productService.getProductTopSales();

		// 로그인 되어 있다면 회원 등급, 프로필 이미지 정보
		Map<String, Object> userInfo = null;
		// 로그인 중이라면
		if (session.getAttribute("login_id") != null) {
			String member_id = session.getAttribute("login_id").toString();
			userInfo = userService.getUserProfileImgUrlAndGradeName(member_id);
		}

		model.addAttribute("productList", productList);
		model.addAttribute("pageShop", pageShop);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("topProduct", topProduct);
		model.addAttribute("userInfo", userInfo);
		return "shop/main/shop_admin";
	}

	@RequestMapping(value = "/list/category", method = RequestMethod.POST)
	public String getTotalNumWithCategory(@RequestBody Map<String, Integer> map, Model model) {

		int category_id = map.get("category_id");
		int brand_id = map.get("brand_id");
		int totalPage = (int) Math.ceil((productService.getPageTotalNum(category_id, brand_id) / (double) 10.0));
		log.info(totalPage);
		PageShop pageShop = new PageShop(1, totalPage);

		pageShop.setCategory_id(category_id);
		pageShop.setBrand_id(brand_id);
		List<ProductVO> productList = productService.getProductList(pageShop);

		model.addAttribute("productList", productList);
		model.addAttribute("pageShop", pageShop);
		return "shop/main/productDivUser";

	}

	@RequestMapping(value = "/list/category/admin", method = RequestMethod.POST)
	public String getTotalNumWithCategoryAdmin(@RequestBody Map<String, Integer> map, Model model) {
//		log.info("카테고리 관리자  =================================================");

		int category_id = map.get("category_id");
		int totalPage = (int) Math.ceil((productService.getPageTotalNumAll(category_id) / (double) 10.0));

		PageShop pageShop = new PageShop(1, totalPage);

		pageShop.setCategory_id(category_id);
		List<ProductVO> productList = productService.getProductListAll(pageShop);

		model.addAttribute("productList", productList);
		model.addAttribute("pageShop", pageShop);
		return "shop/main/productDivUser";

	}

	// 상품 더보기
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listAjaxPost(@RequestBody PageShop pageShop, Model model) {
//		log.info("ajax 상품 더보기 요청 ---------------------------------------");
		int category_id = pageShop.getCategory_id();
		int brand_id = pageShop.getBrand_id();

		// pageShop 초기화
		int totalPage = (int) Math.ceil((productService.getPageTotalNum(category_id, brand_id) / (double) 10.0));

		pageShop.setTotalPage(totalPage);
		pageShop.setStartNum((pageShop.getCurrentPage() - 1) * 10);
		pageShop.setEndNum(10);

		// 공개 상품 리스트
		List<ProductVO> productList = productService.getProductList(pageShop);
//		log.info(productList);
		model.addAttribute("productList", productList);
		model.addAttribute("pageShop", pageShop);
		return "shop/main/productDivUser";
	}

	// 상품 더보기 관리자
	@RequestMapping(value = "/list/admin", method = RequestMethod.POST)
	public String listAjaxAdminPost(@RequestBody PageShop pageShop, Model model) {
//		log.info("ajax 관리자 상품 더보기 요청 ---------------------------------------");
		int category_id = pageShop.getCategory_id();
		// pageShop 초기화
		int totalPage = (int) Math.ceil((productService.getPageTotalNumAll(category_id) / (double) 10.0));
		pageShop.setTotalPage(totalPage);
		pageShop.setStartNum((pageShop.getCurrentPage() - 1) * 10);
		pageShop.setEndNum(10);
		// 전체 상품 리스트
		List<ProductVO> productList = productService.getProductListAll(pageShop);
//		log.info(productList);
		model.addAttribute("productList", productList);
		model.addAttribute("pageShop", pageShop);
		return "shop/main/productDivAdmin";
	}

	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	public String popUp(Model model) {
		ProductVO productVO = productService.getNewestProduct();
		model.addAttribute("item", productVO);
		return "shop/main/popup";
	}

}
