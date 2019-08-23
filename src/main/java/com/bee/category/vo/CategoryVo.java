package com.bee.category.vo;

import java.util.List;

public class CategoryVo {
	
	    private Integer id;
	    
	    private String name;
	   	    
	    private List<CategoryVo> childrenCategory;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<CategoryVo> getChildrenCategory() {
			return childrenCategory;
		}

		public void setChildrenCategory(List<CategoryVo> childrenCategory) {
			this.childrenCategory = childrenCategory;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		
		
		
	    
	    

}
