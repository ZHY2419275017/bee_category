package com.bee.category.mapper;

import com.bee.category.pojo.MallCategory;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface MallCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallCategory record);

    MallCategory selectByPrimaryKey(Integer id);

    List<MallCategory> selectAll();

    int updateByPrimaryKey(MallCategory record);
    
    int updateByPrimaryKeySelective(MallCategory record);
    //根据父节点查找该节点的所有直接子节点
    List<MallCategory> selectCategoryChildrenByParentId(Integer parentId);
   
}