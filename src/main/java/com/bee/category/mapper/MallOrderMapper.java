package com.bee.category.mapper;

import com.bee.category.pojo.MallOrder;
import java.util.List;

public interface MallOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallOrder record);

    MallOrder selectByPrimaryKey(Integer id);

    List<MallOrder> selectAll();

    int updateByPrimaryKey(MallOrder record);
}