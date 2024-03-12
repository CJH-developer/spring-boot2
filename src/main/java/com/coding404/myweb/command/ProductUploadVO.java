package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUploadVO {
	
	private Integer upload_vo;
	private String filename; // 실제 파일명
	private String filepath; // 업로드될 날짜폴더
	private String uuid; // 랜덤값
	private LocalDateTime regdate; // 동록일
	private Integer prod_id; // fk
	private String prod_writer; // fk
}
