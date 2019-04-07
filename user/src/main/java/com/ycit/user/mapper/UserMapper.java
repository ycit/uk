package com.ycit.user.mapper;

import com.ycit.common.bean.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User findByUsername(@Param("username")String username);

}
