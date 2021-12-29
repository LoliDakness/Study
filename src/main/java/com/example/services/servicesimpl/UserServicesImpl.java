package com.example.services.servicesimpl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.config.Result;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.services.MailService;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: yangjie
 * @data:2021/12/22 15:19
 */
@Service
public class UserServicesImpl implements UserService{
    @Autowired
    private UserMapper us;
    @Autowired
    private MailService ms;
    /**
     * 注册账号
     * @param user
     * @return
     */
    public Result createUser(User user){
        int res=us.selectByEmailName(user.getEmail());
        if(res>0){
            return new Result(Result.ERROR_CODE,"该账号已经注册");
        }
        // 雪花算法生成验证码
        String confirmCode= IdUtil.getSnowflake(1,1).nextIdStr();
        //yan
        String salt= RandomUtil.randomString(5);
        String md5Pwd= SecureUtil.md5(user.getPassword()+salt);
        //激活失效时间
        LocalDateTime ldt=LocalDateTime.now().plusDays(1);
        //初始化账号信息
        user.setSalt(salt);
        user.setPassword(md5Pwd);
        user.setConfirmCode(confirmCode);
        user.setActivitionTime(ldt);
        user.setIsValid((byte)0);
        //新增账号
        int result=us.insertUser(user);
        if(result>0){
            //发送邮件
            String activitionUrl="http://localhost:9001/user/activition?confirmCode="+confirmCode;
            ms.sendMailForActivitionUser(activitionUrl,user.getEmail());
            return new Result(Result.SUCCESS_CODE,"注册成功,请前往邮箱激活");
        }else{
            return new Result(Result.ERROR_CODE,"注册失败");
        }

    }

    /**
     * 登录账号
     */
    public Result loginUser(User user){
        //根据邮箱查询用户
        List<User> list=us.selectUserByEmail(user.getEmail());
        //查询不到结果返回该用户不存在或未激活
        if(list==null||list.isEmpty()){
            return new Result(Result.ERROR_CODE,"该账户不存在或未激活");
        }
        //查询到多个用户。返回账户异常
        if(list.size()>1){
            return new Result(Result.ERROR_CODE,"账号异常，清联系管理员");
        }
        //查询到一个用户，进行密码比对
        User u=list.get(0);
        //用户输入的密码和yan加密
        String md5Pwd=SecureUtil.md5(user.getPassword()+u.getSalt());
        if(!u.getPassword().equals(md5Pwd)){
            return new Result(Result.ERROR_CODE,"密码错误");
        }
        return new Result(Result.SUCCESS_CODE,"成功登录");
    }

    /**
     * 激活账号
     */
    public Result activitionUser(String confirmCode){
        //根据确认码查询用户
        User user=us.selectUserByConfirm(confirmCode);
        //判断激活时间是否激活
        boolean after=LocalDateTime.now().isAfter(user.getActivitionTime());
        if(after){
            return  new Result(Result.ERROR_CODE,"链接已失效");
        }
        //根据确认码查询用户修改状态值为1
        int result=us.updateUserByConfirm(confirmCode);
        if(result>0){
            return new Result(Result.SUCCESS_CODE,"激活成功！");
        }else{
            return new Result(Result.ERROR_CODE,"激活失败！");
        }
    }
}
