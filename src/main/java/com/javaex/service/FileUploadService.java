package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public String restore(MultipartFile file) {
		System.out.println("[FileUploadService] : restore");
		System.out.println(file.getOriginalFilename());
		
		////db저장할 정보 수집
		//오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName : "+orgName);
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName : "+ exName);
		
		//저장파일 이름
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		//서버패스 -->저장경로
		//C:\\javaStudy\\upload
		String saveDir = "C:\\javaStudy\\upload";
		String filePath = saveDir +"\\"+ saveName;
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		//파일 저장하기
		
		try {
			byte[] fileData =file.getBytes();
			
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return saveName;
	}

	
}
