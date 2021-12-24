package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: yangjie
 * @data:2021/12/22 14:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
    private Integer id; //主键
    private String email; //邮箱
    private String password; //密码
    private String salt; //yan
    private String confirmCode; //确认码
    private LocalDateTime activitionTime; //激活失效时间
    private Byte isValid; //是否可用
}
