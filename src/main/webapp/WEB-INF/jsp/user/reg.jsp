<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function(){
        $('#jsp_user_reg_regFrom').form({
            url:'${pageContext.request.contextPath}/user/reg',
            success:function(r){
                var result = jQuery.parseJSON(r);
                if(result.result==1){
                    $('#jsp_user_reg_regDialog').dialog('close');
                }
                $.messager.show({
                    title:'提示',
                    msg:result.message
                });
            }
        });
        $('#jsp_user_reg_regFrom input').on('keyup',function(event){
            if(event.keyCode == '13'){
                $('#jsp_user_reg_regFrom').submit();
            }
        });
    });
</script>
<div id="jsp_user_reg_regDialog"style="width: 250px" class="easyui-dialog" data-options="title:'注册',modal:true,closable:false,closed:true,
        buttons:[{
            text:'注册',
            iconCls:'icon-edit',
            handler:function(){
                $('#jsp_user_reg_regFrom').submit();
            }
          }]">
    <form id="jsp_user_reg_regFrom" method="post">
        <table>
            <tr>
                <th>登录名</th>
                <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入登录名！'"/></td>
            </tr>
            <tr>
                <th>密码</th>
                <td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入密码！'"/></td>
            </tr>
            <tr>
                <th>重复密码</th>
                <td><input name="rePwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请再次输入密码！',validType:'eqPassword[\'#jsp_user_reg_regFrom input[name=pwd]\']'"/></td>
            </tr>
        </table>
    </form>
</div>