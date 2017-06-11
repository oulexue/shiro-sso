package com.taotao.controller;

import java.util.HashMap;
import java.util.Map;

import org.csource.test.FastDFSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.pojo.PictureResult;
import com.taotao.service.PictureService;

@Controller
public class PictureContoller {

	@Autowired
	PictureService pictureService;
	private final String IMAGE_URL="http://172.18.39.29/";
	/*@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile){
		return pictureService.uploadFile(uploadFile);
	}*/
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult upload(MultipartFile uploadFile){
		try {
			//1、取文件的扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			//2、创建一个FastDFS的客户端
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:fdfs_client.conf");
			//3、执行上传处理
			String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);//group1/M0/00/00/SADFADF.JPG
			//4、拼接返回的url和ip地址，拼装成完整的url(nginx地址)
			String url = IMAGE_URL + path;
			return PictureResult.ok(url);
		} catch (Exception e) {
			e.printStackTrace();
			return PictureResult.error("图片上传失败");
		}
	}
}
