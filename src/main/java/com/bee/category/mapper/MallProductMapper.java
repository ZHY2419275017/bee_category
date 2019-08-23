package com.bee.category.mapper;

import com.bee.category.pojo.MallProduct;
import java.util.List;

public interface MallProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallProduct record);

    MallProduct selectByPrimaryKey(Integer id);

    List<MallProduct> selectAll();

    int updateByPrimaryKey(MallProduct record);
}