package com.coding404.myweb.product.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	public String makeFolder() {
		String filePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		
		File file = new File(uploadPath + "/" + filePath);
		if(file.exists() == false ) { // 해당 파일이 있으면 true
			file.mkdirs();
		}
		return filePath;
	}
	
	@Override
	// 실행 도중 에러 발생시 자동 rollback 처리를 한다. 단, catch를 통해서 예외처리시 transaction이 동작하지 않는다.
	@Transactional(rollbackFor = Exception.class)
	public int regist(ProductVO vo, List<MultipartFile> list) {
		
		int result = productMapper.regist(vo);
		
		// MultipartFile 비어있으면, 필터링
		list = list.stream().filter(m -> m.isEmpty() == false).collect(Collectors.toList());
		
		for(MultipartFile file : list) {
			String filename = file.getOriginalFilename();
			filename = filename.substring(filename.lastIndexOf("\\") + 1);
			// 2. 파일 사이즈
			long size = file.getSize();
		
			// 동일한 파일로 업로드가 되면, 기존 파일이 덮어지기 때문에, 랜덤한 이름을 이용해서 파일 명칭 바꿈
			String uuid = UUID.randomUUID().toString();
		
			String filepath = makeFolder();
			// 3. 업로드 할 경로
			String savePath = uploadPath + "/" + filepath + "/" + uuid + "_" + filename; 
			
			System.out.println("파일명 : " + filename); // DB에 원본 파일명 저장
			System.out.println("폴더명 : " + filepath); // DB에 폴더명 저장
			System.out.println("랜덤이름 : " + uuid); // DB 저장
			System.out.println("파일 사이즈 : " + size);
			System.out.println("업로드 할 경로 : " + savePath);
			
			
			try {
				File saveFile = new File(savePath);
				file.transferTo(saveFile);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			// 업로드 이후에 데이터베이스에 경로를 저장
			// prod_id 는 service 영역에서 구할 수 있는 방법이 없다.
			productMapper.registFile(ProductUploadVO.builder().filename(filename)
															  .filepath(filepath)
															  .uuid(uuid)
															  /* .prod_id(null) */
															  .prod_writer(vo.getProd_writer()).build());
		}
		return result;
	}

	@Override
	public ArrayList<ProductVO> getList(Criteria criteria, String user_id) {
		return productMapper.getList(criteria, user_id);
	}

	@Override
	public ProductVO getDetail(int prod_id) {
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int update(ProductVO vo) {
		return productMapper.update(vo);
	}

	@Override
	public int delete(int prod_id) {
		return productMapper.delete(prod_id);
	}

	@Override
	public int getTotal(Criteria criteria, String user_id) {	
		return productMapper.getTotal(criteria, user_id);
	}

	@Override
	public ArrayList<CategoryVO> getCategory() {
		
		return productMapper.getCategory();
	}

	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		
		return productMapper.getCategoryChild(vo);
	}

	@Override
	public ArrayList<ProductUploadVO> getImges(int prod_id) {
		return productMapper.getImges(prod_id);
	}
	
}
