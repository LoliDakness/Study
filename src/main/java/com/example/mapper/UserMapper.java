package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: yangjie
 * @data:2021/12/22 14:31
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据确认码查询用户
     */
    User selectUserByConfirm(@Param("confirmCode") String confirmCode);

    /**
     * 根据确认码修改用户状态值为1(1可用，0可用)
     */
    int updateUserByConfirm(@Param("confirmCode") String confirmCode);


    /**
     *根据邮箱查询用户
     * @param email
     * @return
     */
    List<User> selectUserByEmail(@Param("email") String email);
    
    /**
     * 根据邮箱查询是否有相同邮箱
     */
    int selectByEmailName(@Param("email") String email);
}
