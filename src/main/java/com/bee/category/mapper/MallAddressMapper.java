package com.bee.category.mapper;

import com.bee.category.pojo.MallAddress;
import java.util.List;

public interface MallAddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallAddress record);

    MallAddress selectByPrimaryKey(Integer id);

    List<MallAddress> selectAll();

    int updateByPrimaryKey(MallAddress record);
}