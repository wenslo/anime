package org.wen.service;

import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.User;

import java.util.List;
import java.util.Map;

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

    /**
     * 用户模块的分页
     * @param name 名字
     * @param page 页数
     * @param rows 行数
     * @return
     */
    public DataGrid datagrid(String name,int page,int rows);

    /**
     * 删除用户
     * @param ids
     * @return
     */
    public Result deleteUser(String ids);

    /**
     * 查找用户
     * @param id
     * @return
     */
    public Result findUser(Long id);

    /**
     * 修改用户
     * @param id  ID
     * @param name 名字
     * @param pwd 密码
     * @return
     */
    public Result updateUser(Long id, String name, String pwd);

    /**
     * 查找需要导出为iExcel的数据
     * @param ids
     * @return
     */
    public List<Map<String,Object>> queryMap(String ids);

    /**
     * 导入Excel，批量添加
     * @param users
     */
    public void addUsers(List<User> users);
}
