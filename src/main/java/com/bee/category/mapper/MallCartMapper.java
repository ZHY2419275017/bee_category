package com.bee.category.mapper;

import com.bee.category.pojo.MallCart;
import java.util.List;

public interface MallCartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallCart record);

    MallCart selectByPrimaryKey(Integer id);

    List<MallCart> selectAll();

    int updateByPrimaryKey(MallCart record);
}