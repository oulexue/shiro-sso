package com.taotao.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.taotao.EasyUIRequest;
import com.taotao.EasyUIResponse;
import com.taotao.pojo.Item;
import com.taotao.pojo.ItemDesc;
import com.taotao.pojo.ParamItem;
import com.taotao.pojo.PictureResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.service.PictureService;
/**
 * 
     * Title: ItemController.java    
     * Description: 商品管理类
     * @author xyz       
     * @created 2017-5-10 上午8:43:18
 */
@Controller
@RequestMapping("item")
public class ItemController {
	/**
	 * 
	     * @discription easyui的分页查询
	     * @author xyz       
	     * @created 2017-5-10 上午8:42:48     
	     * @return
	 */
	@Autowired
	ItemService itemService;
	
	@Autowired
	PictureService pictureService;
	
	@RequiresPermissions("listItem")
//	@RequiresRoles("admin")  
	@RequestMapping("list")
	@ResponseBody
	public EasyUIResponse listByPage(EasyUIRequest param){
		//查询出的分页对象
		PageInfo<Item> page = itemService.listByPage(param.getPage(), param.getRows());
		//因为前台easyui要求返回固定数据格式，所以分页对象要拆分
		EasyUIResponse res = new EasyUIResponse();
		
		res.setRows(page.getList());
		res.setTotal(page.getTotal());
		return res;
	}
	@RequestMapping("save")
	@ResponseBody
	public TaotaoResult save(Item item,String itemParams,String desc){//Model
		//保存商品
		itemService.save(item,itemParams,desc);
		return TaotaoResult.ok();
		 
	}
	@RequestMapping("showDesc")
	public String showDesc(Long itemId,Map map){//Model
		//查询商品描述，放在request里
		ItemDesc desc = itemService.findDescByItemId(itemId);
		List<ParamItem> jsonObj = itemService.findParamsByItemId(itemId);
		map.put("desc", desc);
		map.put("jsonObj", jsonObj);
		return "item_desc";
	}
	@RequestMapping("/upload")
    public void upload(@RequestParam("filedata") MultipartFile file,HttpServletResponse   response) throws IOException {
        PictureResult result = pictureService.uploadFile(file);
		this.uploadSuccess(result, file.getOriginalFilename(), response);
        return ;
    }

	/**
	 * 按xheditor指定的参数返回，否则会出现问题
	 * @param result
	 * @param fileName
	 * @param response
	 */
	private void uploadSuccess(PictureResult result,String fileName, HttpServletResponse   response) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("err", "");//这些字段都是固定的  err表示错误，必须为空
		Map<String, Object> nm = new HashMap<String, Object>();
		nm.put("url", "!"+result.getUrl());//查看时，图片默认调用的路径 ，如果要回显图片必须加!
		nm.put("localfile", fileName);//本地文件名称
		m.put("msg", nm);
		nm.put("id", 0);
		this.writeJson(m,response);
	}
	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	private void writeJson(Object object, HttpServletResponse   response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
