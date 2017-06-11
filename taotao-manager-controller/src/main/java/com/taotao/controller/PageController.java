package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
     * Title: PageController.java    
     * Description: 这个类是用来做页面跳转的
     * @author xyz       
     * @created 2017-5-9 下午4:02:31
 */
@Controller
public class PageController {
	/**
	 * 
	     * @discription 这个方法是当前端有请求过来时，直接跳转到指定页面
	     * @author xyz       
	     * @created 2017-5-9 下午4:17:15     
	     * @param page
	     * @return
	 */
	@RequestMapping("{page}")
	public String page(@PathVariable("page") String page){
		return page;
	}
}
