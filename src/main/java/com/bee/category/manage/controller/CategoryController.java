package com.bee.category.manage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bee.category.pojo.MallCategory;
import com.bee.category.pojo.MallUser;
import com.bee.category.service.ICategoryService;
import com.bee.category.vo.CategoryVo;

@RestController
@Controller
@RequestMapping(value="/category")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
	private Logger logger = LoggerFactory.getLogger(CategoryController.class);
	
	/**
	 * 
	 * 添加分类
	 * @param categoryName
	 * @param parentId
	 * @return
	 */	
	@GetMapping(value="/add")
	public String addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue = "0") Integer parentId){
		MallUser user = (MallUser)session.getAttribute("user");
		if(null==user){
			return "notLoginError";
		}
		//判断是否是管理员
		if(user.getRole().equals("0")){
			//是管理员，可以添加分类
			return categoryService.addCategory(categoryName, parentId);
		}
		return "noAuthError";
	}
	
	/**
	 * 修改分类名
	 *
	 * @param newCategoryName
	 * @param categoryId
	 * @return
	 * @author ZHAOHUIYU
	 */
	@GetMapping(value="/update")
	public String setCategoryName(HttpSession session,String newCategoryName,Integer categoryId ){
		MallUser user = (MallUser)session.getAttribute("user");
		if(null==user){
			return "notLoginError";
		}
		//判断是否是管理员
		if(user.getRole().equals("0")){
			//是管理员，更新分类			
			return categoryService.updateCategory(newCategoryName,categoryId);
		}
		return "noAuthError";
	}
	/**
	 * 查找所有父节点是categoryId的节点
	 * @param categoryId
	 * @return 节点的集合
	 */
	@GetMapping(value="/getCategory")
	public List<MallCategory> getChildrenParalleCategory( @RequestParam(value = "categoryId",defaultValue = "0") Integer categoryId){
		logger.info("getCategory方法被访问  categoryId是"+categoryId);
		return categoryService.getChildrenParalleCategory(categoryId);
	}
	/**
	 * 获取所有分类的根节点
	 * @return
	 */
	@GetMapping(value="/getRootCategory")
	public List<MallCategory> getRootCategory(){
		logger.info("getRootCategory方法被访问");
		return categoryService.getRootCategory();		
	}
	/**
	 * 查询该节点下的所有的分类节点
	 * @param categoryId
	 * @return
	 */
	@GetMapping(value="/getDeepCategory")
	public List<MallCategory> selectCategoryAndDeepChildrenCategoryById(@RequestParam(value="categoryId",defaultValue="0") Integer categoryId){
		logger.info("getDeepCategory方法被访问");
		return categoryService.selectCategoryAndChildrenById(categoryId);
	}
	
	
	@GetMapping(value="/getAllCategory")
	public List<CategoryVo> selectAllCategory(){
		logger.info("getAllCategory方法被访问");
		return categoryService.selectAllCategory();				
	}
	

}
