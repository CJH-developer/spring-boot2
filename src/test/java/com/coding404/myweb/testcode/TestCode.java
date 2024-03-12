package com.coding404.myweb.testcode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.service.ProductMapper;
import com.coding404.myweb.util.Criteria;

@SpringBootTest
public class TestCode {

	@Autowired
	ProductMapper productMapper;
	
//	@Test
//	public void test01() {
//		
//		for(int i = 1; i<=300; i++) {
//			ProductVO vo = ProductVO.builder()
//							.prod_enddate("2024-06-10")
//							.prod_writer("TEST01")
//							.prod_name("test" + i)
//							.prod_price(1000 + i)
//							.prod_count(10 + i)
//							.prod_discount(5 + i)
//							.prod_purchase_yn("N")
//							.prod_content("테스트페이지" + i)
//							.prod_comment("pagetest" + i)
//							.build();
//			productMapper.regist(vo);
//		}
//	}
	
//	@Test
//	public void test01() {
//		
//		productMapper.getList(new Criteria(3, 10));
//		
//	}
//
//	@Test
//	public void test03() {
//		Criteria cri = new Criteria(1, 10);
//		cri.setSearchName("test");
//		cri.setStartDate("2024-02-04");
//		cri.setSearchPrice("desc");
//		productMapper.getList(cri);
//	}
	
	
}