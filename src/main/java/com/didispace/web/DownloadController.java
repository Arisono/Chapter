package com.didispace.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.didispace.util.HttpRequestUtils;

import io.swagger.annotations.Api;

@Api(value = "上传与下载", description = "文件上传和下载接口")
@RestController
public class DownloadController {
    
	/**
	 * 单文件上传
	 * @param request
	 * @param mulits
	 * MultipartHttpServletRequest multiReq,
	 */
	@RequestMapping(value="/uploadSigleFile",method=RequestMethod.POST)
	public String uploadFile(HttpServletRequest request,
			@RequestParam MultipartFile file){		   
	        FileOutputStream fos;
	        FileInputStream fs;
	        System.out.println("文件名："+file.getOriginalFilename());
	        //需要存放文件资源的路径，可以自由选择设置
	        String filePath="C://Users//Arison//Desktop//"+file.getOriginalFilename();
	        filePath=request.getServletContext().getRealPath("/")+file.getOriginalFilename();
			try {
				fos = new FileOutputStream(new File(filePath));
				fs = (FileInputStream) file.getInputStream();
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
	 * 单文件上传
	 * @param request
	 * @param mulits
	 * @RequestParam MultipartHttpServletRequest file 直接放入控制器参数中，需要处理
	 */
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST)
	public String uploadFile2(HttpServletRequest request){		   
	        FileOutputStream fos;
	        FileInputStream fs;
	        MultipartFile file1 = ((MultipartHttpServletRequest) request)
			      .getFile("file");
//	        MultipartFile file1=file.getFile("file");
	        System.out.println("文件名："+file1.getOriginalFilename());
	        //需要存放文件资源的路径，可以自由选择设置
	        String filePath="C://Users//Arison//Desktop//"+file1.getOriginalFilename();
	        filePath=request.getServletContext().getRealPath("/");
			try {
				fos = new FileOutputStream(new File(filePath));
				fs = (FileInputStream) file1.getInputStream();
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
	 * 多文件上传
	 * @desc 使用MultipartHttpServletRequest
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
	
	
	/**
	 * 多文件上传
	 * @desc 使用MultipartHttpServletRequest
	 * @param request
	 * @param mulits
	 */
	@RequestMapping(value="/uploadMulitFiles",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> uploadFiles2(HttpServletRequest request
			,@RequestParam MultipartFile[] file){
			    MultipartFile file1 = null;
			    BufferedOutputStream stream = null;
			    Map<String,Object> result=new HashMap<>();
			    result.put("params", HttpRequestUtils.getHttpMessage(request));
			    File f=new File("src/main/resources/uploads/");
			    String path=f.getAbsolutePath();
			    if (file==null) {
			    	result.put("msg", "upload failed  file param lost!");
					return result;
				}
			    for (int i = 0; i < file.length; ++i) {
			      file1 = file[i];
                 System.out.println("上传保存路径："+file1.getOriginalFilename());
			      if (!file1.isEmpty()) {
			        try {
			          byte[] bytes = file1.getBytes();
			          stream = new BufferedOutputStream(new FileOutputStream(
			              new File(path+"/"+file1.getOriginalFilename())));
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
	
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> download(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		   String fileName = "第一行代码（第2版）.pdf";
	        if (fileName != null) {
	            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
	            String realPath ="C:/Project/spring-boot/Chapter/"+"src/main/resources/uploads/";
	            realPath=request.getServletContext().getRealPath("/");
	            System.out.println("下载地址："+realPath);
	            File file = new File(realPath, fileName);
	            if (file.exists()) {
	                response.setContentType("application/force-download");// 设置强制下载不打开
	                response.addHeader("Content-Disposition",
	                        "attachment;fileName=" +  URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
	                byte[] buffer = new byte[1024];
	                FileInputStream fis = null;
	                BufferedInputStream bis = null;
	                try {
	                    fis = new FileInputStream(file);
	                    bis = new BufferedInputStream(fis);
	                    OutputStream os = response.getOutputStream();
	                    int i = bis.read(buffer);
	                    while (i != -1) {
	                        os.write(buffer, 0, i);
	                        i = bis.read(buffer);
	                    }
	                    System.out.println("success");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                } finally {
	                    if (bis != null) {
	                        try {
	                            bis.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    if (fis != null) {
	                        try {
	                            fis.close();
	                        } catch (IOException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                }
	            }
	        }
	        return null;

	}
}
