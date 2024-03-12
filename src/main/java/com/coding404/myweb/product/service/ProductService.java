package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {

	public int regist(ProductVO vo, List<MultipartFile> list);
	public ArrayList<ProductVO> getList(Criteria criteria, String user_id);
	public int getTotal(Criteria criteria, String user_id); //전체게시글 수

	
	public ProductVO getDetail (int prod_id);
	public int update(ProductVO vo);
	public int delete(int prod_id);
	
	// 카테고맆 관련
	public ArrayList<CategoryVO> getCategory();
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	// 이미지 조회 기능
	public ArrayList<ProductUploadVO> getImges(int prod_id);
}
