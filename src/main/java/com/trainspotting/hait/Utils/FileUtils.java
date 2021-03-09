package com.trainspotting.hait.Utils;

import java.io.File;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {
	
	
	@Autowired
	private ServletContext ctx;
	
	public void makeFolders(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}
	
	public String getBasePath(String... moreFolder) {
		String temp = "";
		for(String s : moreFolder) {
			temp += s;
		}
		String basePath  = ctx.getRealPath(temp);		
		return basePath;
	}


	public String getExt(String fileNm) {
		return fileNm.substring(fileNm.lastIndexOf(".") + 1);
	}

	
	public String getRandomFileNm(String fileNm) {
		return UUID.randomUUID().toString() + "." + getExt(fileNm);
	}
	
	
	public String transferTo(MultipartFile mf, String...target) {
		String fileNm = null;
		String basePath = getBasePath(target);
		makeFolders(basePath);
		try {
			fileNm = getRandomFileNm(mf.getOriginalFilename());
			File file = new File(basePath, fileNm); 
			mf.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return fileNm;
	}
	
}
