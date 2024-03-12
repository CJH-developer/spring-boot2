package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.PageVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductService;
import com.coding404.myweb.util.Criteria;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	

	@GetMapping("/productList")
	public String list(Model model, Criteria criteria, HttpSession session) { //매개변수가 없으면 기본값, 잉스면 생성자를 
		
		
		String user_id = (String)session.getAttribute("user_id");
		
		//목록을 가지고 나와서 데이터를 담고 나감
		ArrayList<ProductVO> list = productService.getList(criteria, user_id); //데이터
		int total = productService.getTotal(criteria, user_id);
		PageVO vo = new PageVO(criteria, total); //페이지네이션
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", vo);
		
		System.out.println(vo.toString());
		
		return "product/productList";
	}
	
	@GetMapping("/productReg")
	public String reg() {
		return "product/productReg";
	}
	
	@GetMapping("/productDetail")
	public String detail(@RequestParam("prod_id") int prod_id,
										Model model) {
		
		// 상품내용
		ProductVO vo = productService.getDetail(prod_id);
		// 이미지들
		ArrayList<ProductUploadVO> imgs = productService.getImges(prod_id);
		model.addAttribute("vo", vo);
		model.addAttribute("imgs", imgs);
		
		return "product/productDetail";
	}
	
	
	//상품등록요청
	@PostMapping("/productForm")
	public String productForm(ProductVO vo, RedirectAttributes ra, @RequestParam("file") List<MultipartFile> list) {
		
		// 사진 넘어올 때
		// 1. 공백 이미지 제거
		list = list.stream().filter(m -> m.isEmpty() == false).collect(Collectors.toList());
		// 2. 이미지 타입인지 검사
		for(MultipartFile file : list) {
			
			if(file.getContentType().contains("image") == false) {
				ra.addFlashAttribute("msg", "이미지는 필수로 선택합니다. png/jpg/jpeg/500x500만 가능합니다.");
				return "redirect:/product/productList";
			}
			
		}
		// 3.이미지를 올린 경우는 서비스로 위임
		
		
		int result = productService.regist(vo, list);
		if(result == 1) { //성공
			ra.addFlashAttribute("msg", "정상적으로 처리되었습니다."); //리다이렉트시 1회성
		}else { //실패
			ra.addFlashAttribute("msg", "등록에 실패했습니다. 관리자에게 문의하세요. 8282-8282");
		}
		
		return "redirect:/product/productList";
	}
	
	
	@PostMapping("/updateForm")
	public String updateForm(ProductVO vo, RedirectAttributes ra) {
		
		//1.화면에서 넘어온느 값을 받습니다.
		//2.서비스에서 update 메서드 생성
		//3.enddate, prod_name, price, count, discount, comment, content
		//4.업데이트 성공/실패 여부를 저장해서 목록화면으로 이동.
		
		int result = productService.update(vo);
		
		if(result == 1) { //성공
			ra.addFlashAttribute("msg", "성공"); //리다이렉트시 1회성
		}else { //실패
			ra.addFlashAttribute("msg", "실패");
		}
		
		
		return "redirect:/product/productList";
	}
	
	@PostMapping("/deleteForm")
	public String deleteForm(@RequestParam("prod_id") int prod_id) {
		
		
		productService.delete(prod_id);
		
		
		return "redirect:/product/productList";
	}
	
}