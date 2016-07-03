package org.wen.service;

import org.wen.dto.Result;
import org.wen.entity.DataGrid;

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
    public Result regUser(String name,String pwd) throws Exception;

    /**
     * 用户index页面的登录功能
     * 2016年6月22日20:56:08
     * 温海林
     * @param name
     * @param pwd
     * @return
     */
    public Result login(String name, String pwd);
    public DataGrid datagrid(String name,int page,int rows);
    public Result deleteUser(String ids);

    public Result findUser(Long id);

    public Result updateUser(Long id, String name, String pwd);
}
