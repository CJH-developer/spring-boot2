package com.coding404.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper
public interface ProductMapper {

	public int regist(ProductVO vo);
	public ArrayList<ProductVO> getList(@Param("criteria") Criteria criteria, @Param("user_id") String user_id);
	public int getTotal(@Param("criteria") Criteria criteria, @Param("user_id") String user_id); //전체게시글 수

	
	public ProductVO getDetail (int prod_id);
	public int update(ProductVO vo);
	public int delete(int prod_id);
	
	// 카테고리 
	public ArrayList<CategoryVO> getCategory();
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	//파일업부로드 인서트
	public void registFile(ProductUploadVO vo);
	
	// 이미지 조회 기능
	public ArrayList<ProductUploadVO> getImges(int prod_id);
	
}
