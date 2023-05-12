package com.mz.hat.dao;

import com.mz.hat.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Integer signUp(UserVo userVo);

    Integer email_check(String user_email);

    UserVo signIn(UserVo userVo);
}
