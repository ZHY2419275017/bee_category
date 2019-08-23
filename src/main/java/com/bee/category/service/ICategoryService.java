package com.bee.category.service;

import java.util.List;

import com.bee.category.pojo.MallCategory;
import com.bee.category.vo.CategoryVo;

public interface ICategoryService {
	//管理员增加分类
	String addCategory(String categoryName,Integer parentId);
	//管理员修改分类名
	String updateCategory(String newCategoryName,Integer categoryId); 
	//获取平行的子分类节点
	List<MallCategory> getChildrenParalleCategory(Integer categoryId);
	//获取所有的根节点
	List<MallCategory> getRootCategory();
	
	List<MallCategory> selectCategoryAndChildrenById(Integer categoryId);
	
	List<CategoryVo>  selectAllCategory();
}
