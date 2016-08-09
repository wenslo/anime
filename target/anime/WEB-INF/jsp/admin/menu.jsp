<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $("#jsp_admin_menu_datagrid").datagrid({
        url:'${pageContext.request.contextPath}/menu/datagrid',
        fit : true,
        fitColumns : true,
        border : false,
        pagination : true,
        idField : 'id',
        pageSize : 10,
        pageList : [ 10, 20, 30, 40, 50 ],
        sortName : 'name',
        sortOrder : 'asc',
        /*pagePosition : 'both',*/
        checkOnSelect : false,
        selectOnCheck : false,
        frozenColumns:[[
            {
                field:'id',
                title:'编号',
                width:100,
                checkbox : true
            },
            {
                field:'pid',
                title:'上级菜单',
                width:150,
                formatter : function(value, row, index) {

                }
            },{
                field:'text',
                title:'菜单描述',
                width:150
            },{
                field:'url',
                title:'URL',
                width:150
            }
        ]],
        toolbar:[
            {
                text:'增加',
                iconCls:'icon-add',
                handler:function(){
                    append();
                }
            },'-',{
                text:'删除',
                iconCls:'icon-remove',
                handler:function(){
                    remove();
                }
            }
        ]
    });
</script>
<div id="jsp_admin_menu_layout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false," style="height:200px;">
        <table id="jsp_admin_menu_datagrid">

        </table>
    </div>
</div>
<div id="jsp_admin_menu_addDialog" data-options="closed:true,modal:true,
                        title:'添加用户',
                        buttons:[{
                        text:'增加',
                        iconCls:'iconCls',
                        handler:function(){
                            $('#jsp_amdin_menu_addForm').form('submit',{
                                url:'${pageContext.request.contextPath}/user/add',
                                success:function(r){
                                    var obj = jQuery.parseJSON(r);
                                    if(obj.result == 1){
                                        $('#jsp_admin_menu_datagrid').datagrid('insertRow',{
                                            index:0,
                                            row:obj.data
                                        });
                                        $('#jsp_admin_menu_addDialog').dialog('close');
                                    }
                                    if(obj.result == 2){
                                        $('#jsp_admin_menu_addDialog').dialog('close');
                                    }
                                    $.messager.show({
                                        title:'提示',
                                        msg:obj.message
                                    });
                                }
                            });
                        }
                    }]" class="easyui-dialog" style="width: 418px;height: 102px;">
    <form id="jsp_amdin_menu_addForm">
        <table>
            <tr>
                <th>登录名称</th>
                <td>
                    <input id="jsp_amdin_menu_addForm_name" name="name" class="easyui-validatebox" data-options="required:true"/>
                </td>
                <th>密码</th>
                <td><input id="jsp_amdin_menu_addForm_pwd" type="password" name="pwd" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>
