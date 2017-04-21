package com.didispace.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.didispace.util.HttpRequestUtils;

import groovy.util.logging.Log4j;
import io.swagger.annotations.Api;

@Api(value = "上传与下载", description = "文件上传和下载接口")
@RestController
public class DownloadController {
    
	/**
	 * 上传文件
	 * @param request
	 * @param mulits
	 * MultipartHttpServletRequest multiReq,
	 */
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	public String uploadFile(HttpServletRequest request,
			@RequestParam MultipartFile files1){
		   
	        FileOutputStream fos;
	        FileInputStream fs;
	        System.out.println("文件名："+files1.getOriginalFilename());
	        String filePath="C://Users//Arison//Desktop//"+files1.getOriginalFilename();
			try {
				fos = new FileOutputStream(new File(filePath));
				fs = (FileInputStream) files1.getInputStream();
				  byte[] buffer=new byte[1024];  
			        int len=0;  
			        while((len=fs.read(buffer))!=-1){  
			            fos.write(buffer, 0, len);  
			        }  
			        fos.close();  
			        fs.close();  
			} catch (IOException e) {
				e.printStackTrace();
			}
			return filePath;  
	}
	
	
	/**
	 * 上传文件
	 * @param request
	 * @param mulits
	 */
	@RequestMapping(value="/uploadFiles",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> uploadFiles(HttpServletRequest request){
		  List<MultipartFile> files = ((MultipartHttpServletRequest) request)
			        .getFiles("file");
			    MultipartFile file = null;
			    BufferedOutputStream stream = null;
			    Map<String,Object> result=new HashMap<>();
			    result.put("params", HttpRequestUtils.getHttpMessage(request));
			    File f=new File("src/main/resources/uploads/");
			    String path=f.getAbsolutePath();
			    if (files==null) {
			    	result.put("msg", "upload failed  file param lost!");
					return result;
				}
			    for (int i = 0; i < files.size(); ++i) {
			      file = files.get(i);
                 System.out.println("上传保存路径："+file.getOriginalFilename());
			      if (!file.isEmpty()) {
			        try {
			          byte[] bytes = file.getBytes();
			          stream = new BufferedOutputStream(new FileOutputStream(
			              new File(path+"/"+file.getOriginalFilename())));
			          stream.write(bytes);
			          stream.close();
			       
			        } catch (Exception e) {
			          stream = null;
			          result.put("msg", "You failed to upload " + i + " => "
				              + e.getMessage());
			          return result;
			        }
			      } else {
			    	  result.put("msg", "You failed to upload " + i
					            + " because the file was empty.");
			        return result;
			      }
			    }
			    result.put("msg", "upload sucess!");
			    return result;
	}
}
