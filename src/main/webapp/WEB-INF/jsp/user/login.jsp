<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function(){
        $('#jsp_user_login_loginForm').form({
            url:'${pageContext.request.contextPath}/user/login',
            success:function(r){
                var result = jQuery.parseJSON(r);
                if(result.result==1){
                    $('#jsp_user_login_loginDialog').dialog('close');
                }
                $.messager.show({
                    title:'提示',
                    msg:result.message
                });
            }
        });
        $('#jsp_user_login_loginForm input').on('keyup',function(event){
            if(event.keyCode == '13'){
                $('#jsp_user_login_loginForm').submit();
            }
        });
        window.setTimeout(function(){
            $('#jsp_user_login_loginForm input[name="name"]').focus();
        },0);
    });
</script>
<div id="jsp_user_login_loginDialog" class="easyui-dialog" data-options="title:'登录',modal:true,closable:false,
        buttons:[{
          text:'注册',
          iconCls:'icon-edit',
          handler:function(){
            $('#jsp_user_reg_regDialog').dialog('open');
          }
          },{
            text:'登录',
            iconCls:'icon-help',
            handler:function(){
              $('#jsp_user_login_loginForm').submit();
            }
        }]">
  <form id="jsp_user_login_loginForm" method="post">
    <table>
      <tr>
        <th>登录名</th>
        <td><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入登录名！'"/></td>
      </tr>
      <tr>
        <th>密码</th>
        <td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请输入密码！'"/></td>
      </tr>
    </table>
  </form>
</div>