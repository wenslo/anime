<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Basic Panel - jQuery EasyUI Demo</title>
  <link rel="stylesheet" type="text/css" href="/anime/resource/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/anime/resource/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="/anime/resource/easyui/demo.css">
  <script type="text/javascript" src="/anime/resource/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="/anime/resource/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/anime/resource/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="/anime/resource/js/whlUtil.js"></script>
  <script type="text/javascript">
    $(function(){

    });
    function regUser(){
        $('#jsp_reg_regFrom').form('submit',{
            url:'${pageContext.request.contextPath}/user/reg',
            success:function(r){
                var result = jQuery.parseJSON(r);
                alert(result.result);
                if(result.result){
                    $('#jsp_reg_regFrom').dialog('close');
                }
                $.messager.show({
                    title:'My Title',
                    msg:'Message will be closed after 5 seconds.'
                });
            }
        });
    }
  </script>
</head>
<body class="easyui-layout">
  <div data-options="region:'north'" style="height:60px;"></div>
  <div data-options="region:'south'" style="height:20px;"></div>
  <div data-options="region:'west',split:true" style="width:200px;">
    <div class="easyui-panel" data-options="title:'ssss',border:false,fit:true"></div>
  </div>
  <div data-options="region:'east'" style="width:200px;"></div>
  <div data-options="region:'center',title:'center title'"></div>

  <div id="jsp_reg_loginDialog" class="easyui-dialog" data-options="title:'登录',modal:true,closable:false,
        buttons:[{
          text:'注册',
          iconCls:'icon-edit',
          handler:function(){
            $('#jsp_reg_regFrom input').val('');
            $('#jsp_reg_regDialog').dialog('open');
          }
          },{
          text:'登录',
          iconCls:'icon-help',
          handler:function(){
            alert('edit')
          }
        }]">
        <table>
          <tr>
            <th>登录名</th>
            <td><input/></td>
          </tr>
          <tr>
            <th>密码</th>
            <td><input/></td>
          </tr>
        </table>
  </div>
  <div id="jsp_reg_regDialog"style="width: 250px" class="easyui-dialog" data-options="title:'注册',modal:true,closable:false,closed:true,
        buttons:[{
            text:'注册',
            iconCls:'icon-edit',
            handler:function(){
              regUser();
              $('#jsp_reg_loginDialog').dialog('open');
              $('#jsp_reg_regDialog').dialog('close');
            }
          }]">
    <form id="jsp_reg_regFrom" method="post">
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
          <td><input name="rePwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'请再次输入密码！',validType:'eqPassword[\'#jsp_reg_regFrom input[name=pwd]\']'"/></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>
