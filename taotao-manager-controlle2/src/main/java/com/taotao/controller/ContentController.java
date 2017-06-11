package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.taotao.EasyUIResponse;
import com.taotao.pojo.Content;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ContentService;
import com.taotao.utils.HttpClientUtil;

  
 /**        
 * Title: ContentController.java    
 * Description: 这是内容管理的类
 * @author xyz       
 * @created 2017-5-16 上午10:46:14    
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	ContentService contentService;
	

	
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIResponse list(Long categoryId,Integer page,Integer rows){
		
		 PageInfo<Content> pageInfo = contentService.findByPage(page, rows, categoryId);
		 //包装成easyui接收的数据格式   
		 EasyUIResponse res = new EasyUIResponse();
		 res.setTotal(pageInfo.getTotal());
		 res.setRows(pageInfo.getList());
		 return res;
	} 
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult addContent(Content content){
		TaotaoResult result = contentService.addContent(content);
		return result;
	}
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult editContent(Content content){
		return contentService.editContent(content);
	}
}
