package com.xiaomi.editor.dao;

import com.xiaomi.editor.bean.UserfileBean;

public interface UserfileBeanMapper {
    int deleteByPrimaryKey(Integer fileId);

    int insert(UserfileBean record);

    int insertSelective(UserfileBean record);

    UserfileBean selectByPrimaryKey(Integer fileId);

    int updateByPrimaryKeySelective(UserfileBean record);

    int updateByPrimaryKey(UserfileBean record);
}