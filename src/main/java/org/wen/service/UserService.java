package org.wen.service;

import org.wen.dto.Result;

/**
 * 用户的业务功能
 * 2016年6月19日21:27:06
 * 温海林
 */
public interface UserService {
    /**
     * 注册用户，将用户名和密码保存入库
     * @param name
     * @param pwd
     * @return
     */
    public Result regUser(String name,String pwd);
}
