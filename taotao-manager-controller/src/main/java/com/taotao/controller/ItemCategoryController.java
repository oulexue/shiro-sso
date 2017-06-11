package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TreeNode;
import com.taotao.service.ItemCategoryService;
@Controller
@RequestMapping("/item/cat")
public class ItemCategoryController {
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@RequestMapping(value="list")
	@ResponseBody
	public List<TreeNode> listTree(@RequestParam(defaultValue="0",value="id")  Long treeId){
		return itemCategoryService.listTree(treeId);
	}
}
