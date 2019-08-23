package com.bee.category.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bee.category.mapper.MallCategoryMapper;
import com.bee.category.pojo.MallCategory;
import com.bee.category.service.ICategoryService;
import com.bee.category.vo.CategoryVo;
@Service
public class CategoryServiceImpl implements ICategoryService {
	
    @Autowired
    private MallCategoryMapper categoryMapper;
    //使用slf4j日志
    private Logger logger = org.slf4j.LoggerFactory.getLogger(CategoryServiceImpl.class);
    
	@Override
	public String addCategory(String categoryName, Integer parentId) {
		if(parentId == null || StringUtils.isEmpty(categoryName)){
			return "argsError";
		}
		MallCategory category = new MallCategory();
		category.setName(categoryName);
		category.setParentId(parentId);
		category.setStatus(true);//这个分类是可用的
		category.setCreateTime(new Date());
		category.setUpdateTime(new Date());
		int rowcount = categoryMapper.insert(category);
		if(rowcount>0){
			return "success";
		}
		return "insertError";		
	}

	@Override
	public String updateCategory(String newCategoryName,Integer categoryId) {
		if(categoryId == null || StringUtils.isEmpty(newCategoryName)){
			return "argsError";
		}		
		MallCategory category = new MallCategory();
		category.setName(newCategoryName);
		category.setId(categoryId);
		int rowcount = categoryMapper.updateByPrimaryKeySelective(category);
		if(rowcount>0){
			return "success";
		}
		return "updateError";	
	}

	@Override
	public List<MallCategory> getChildrenParalleCategory(Integer categoryId) {
		List<MallCategory> categoryList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
		if(null==categoryList){
			logger.info("未找到当前日志的子分类");
		}
		return categoryList;
	}

	@Override
	public List<MallCategory> getRootCategory() {
		List<MallCategory> categoryList = categoryMapper.selectCategoryChildrenByParentId(0);
		if(null==categoryList){
			logger.info("未找到父分类");
		}
		return categoryList;
	}

	@Override
	public List<MallCategory> selectCategoryAndChildrenById(Integer categoryId) {
		//创建空集合接收递归方法的返回值
		Set<MallCategory> categorySet = Sets.newHashSet();
		findChild(categorySet, categoryId);
		//转成List
		List<MallCategory> categoryList = Lists.newArrayList();
		for(MallCategory item : categorySet){
			categoryList.add(item);
		}
		return categoryList;
	}
	
	
	//递归算法，算出子节点
	private Set<MallCategory> findChild(Set<MallCategory> categorySet , Integer categoryId){	
		MallCategory category =  categoryMapper.selectByPrimaryKey(categoryId);
		if(category!=null){		
			categorySet.add(category);
		}
		//查找子节点
		List<MallCategory> categortList = categoryMapper.selectCategoryChildrenByParentId(categoryId);
		for(MallCategory item : categortList){
			findChild(categorySet, item.getId());
		}
		return categorySet;
	}

	@Override
	public List<CategoryVo> selectAllCategory() {		
		List<CategoryVo> categoryVoList = new ArrayList<CategoryVo>();
		//找到所有的根节点
		List<MallCategory> rootCategorys = this.getRootCategory();
		//遍历所有的根节点
		for(MallCategory rootCategory : rootCategorys){
			CategoryVo categoryVo = new CategoryVo();
			categoryVo.setId(rootCategory.getId());
			categoryVo.setName(rootCategory.getName());			
			//根据该根节点的id查找
			List<MallCategory> childrenCategorys = this.getChildrenParalleCategory(rootCategory.getId());
			List<CategoryVo> categoryList = new ArrayList<CategoryVo>();
			for(MallCategory childrenCategory :childrenCategorys ){	
				CategoryVo childrenVo = new  CategoryVo();
				childrenVo.setId(childrenCategory.getId());
				childrenVo.setName(childrenCategory.getName());
				childrenVo.setChildrenCategory(null);
				categoryList.add(childrenVo);
			}			
			categoryVo.setChildrenCategory(categoryList);
			categoryVoList.add(categoryVo);					
		}
		return categoryVoList;
	}

}
