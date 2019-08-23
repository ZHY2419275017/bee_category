package com.bee.category.mapper;

import com.bee.category.pojo.MallComment;
import java.util.List;

public interface MallCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MallComment record);

    MallComment selectByPrimaryKey(Integer id);

    List<MallComment> selectAll();

    int updateByPrimaryKey(MallComment record);
}