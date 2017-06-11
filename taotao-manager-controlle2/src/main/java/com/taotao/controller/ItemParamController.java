package com.taotao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.ItemParam;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
/**        
 * Title: ItemParamController.java    
 * Description: 商品分类和规格的控制类
 * @author xyz       
 * @created 2017-5-12 下午3:45:16    
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("save/{id}")
	@ResponseBody
	public TaotaoResult saveItemParam(@PathVariable("id")Long id, ItemParam itemParam){
		itemParam.setId(null);
		itemParam.setItemCatId(id);
		return itemParamService.save(itemParam);
	}
	
	@RequestMapping("/query/itemcatid/{id}")
	@ResponseBody
	public TaotaoResult queryByCatId(@PathVariable("id") Long id){
		//根据商品类别ID查询其所有规格信息
		return itemParamService.queryByCatId(id);
	}
	
}
