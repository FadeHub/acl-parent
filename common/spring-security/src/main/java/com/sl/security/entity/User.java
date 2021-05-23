package com.sl.security.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shuliangzhao
 * @Title: User
 * @ProjectName acl-parent
 * @Description: TODO
 * @date 2021/5/22 21:35
 */
@Data
@ApiModel
public class User implements Serializable {

    @ApiModelProperty(name = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "用户头像")
    private String salt;

    @ApiModelProperty(value = "用户签名")
    private String token;
}
