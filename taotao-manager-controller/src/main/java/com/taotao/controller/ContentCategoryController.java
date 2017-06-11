package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.ContentCategory;
import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TreeNode;
import com.taotao.service.ContentCategoryService;

/**
 * 
     * Title: ContentCategoryController.java    
     * Description: 这个类是内容分类管理类
     * @author xyz       
     * @created 2017-5-16 上午9:06:14
 */
@Controller
public class ContentCategoryController {

	@Autowired
	ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeNode> listContentTree(@RequestParam(defaultValue="0",value="id") Long id){
		List<TreeNode> listTree = contentCategoryService.listTree(id);
		return listTree;
	}
	@RequestMapping("/content/category/create")
	@ResponseBody
	public TaotaoResult create(Long parentId,String name){
		
		return contentCategoryService.save(parentId,name);
	}
}
