<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $("#jsp_admin_yhgl_datagrid").datagrid({
        url:'${pageContext.request.contextPath}/user/datagrid',
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
            {field:'id',
                title:'编号',
                width:100,
                checkbox : true,
                sortable:true
            }, {field:'name',
                title:'登录名称',
                width:100,
                sortable:true
            }
        ]],
        columns:[[  {field:'pwd',
                title:'密码',
                width:150,
                formatter : function(value, row, index) {
                    return '******';
                }
            }, {field:'createDate',
                title:'创建时间',
                width:150,
                align:'right',
            sortable:true
             }, {field:'updateDate',
                title:'最后修改时间',
                width:100,
                align:'right',
                sortable:true
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
            },'-',{
                text:'修改',
                iconCls:'icon-edit',
                handler:function(){
                    modify();
                }
            },'-',{
                text:'导出',
                iconCls:'icon-more',
                handler:function(){
                    excelExport();
                }
            },'-',{
                text:'导入',
                iconCls:'icon-filter',
                handler:function(){
                    uploadExcel();
                }
            },'-',{
                text:'角色修改',
                iconCls:'icon-large-clipart',
                handler:function(){
                    modifyRole();
                }
            }

        ]
    });
    function searchFun(){
        $("#jsp_admin_yhgl_datagrid").datagrid('load',serializeObject($('#jsp_admin_yhgl_searchForm')));
    }
    function clearFun(){
        $('#jsp_admin_yhgl_layout input[name=name]').val('');
        $("#jsp_admin_yhgl_datagrid").datagrid('load',{});
    }
    function append(){
        $('#jsp_amdin_yhgl_addForm input').val('');
        $("#jsp_admin_yhgl_addDialog").dialog('open');
    }
    function modify(){
        var rows = $('#jsp_admin_yhgl_datagrid').datagrid('getChecked');
        var id = '';
        if(rows.length>1){
            $.messager.alert('提示','对不起，只能选择一条数据！');
        }else if(rows.length<1){
            $.messager.alert('提示','请至少选择一条数据！');
        }else if(rows.length == 1){
            id = rows[0].id;
            $.ajax({
                url : '${pageContext.request.contextPath}/user/'+id+'/showUpdateUser',
                type:'POST',
                dataType:'json',
                success:function(r){
                    var result = r.data;
                    $("#jsp_admin_yhgl_updateDialog").dialog('open');
                    $("#jsp_amdin_yhgl_updateForm_id").val(result.id);
                    $("#jsp_amdin_yhgl_updateForm_name").val(result.name);
//                    $("#jsp_amdin_yhgl_updateForm_pwd").val(result.pwd);
                }
            });
        }
    }
    function remove(){
        var rows = $('#jsp_admin_yhgl_datagrid').datagrid('getChecked');
        var ids = [];
        if(rows.length>0){
            $.messager.confirm('确认','您是否要删除当前选中的项目？',function(r){
                if(r){
                    for(var i=0;i<rows.length;i++){
                        ids.push([rows[i].id]);
                    }
                    $.ajax({
                        url : '${pageContext.request.contextPath}/user/delete',
                        type:'POST',
                        data:{
                            ids:ids.join(',')
                        },
                        dataType:'json',
                        success:function(r){
                            $('#jsp_admin_yhgl_datagrid').datagrid('load');
                            $('#jsp_admin_yhgl_datagrid').datagrid('unselectAll');
                            $.messager.show({
                                title:'提示',
                                msg:r.message
                            });
                        }
                    });
                }
            });
        }else{
            $.messager.show({
                title:'提示',
                msg:'请选择要删除的项目！'
            });
        }
    }
    function excelExport(){
        var rows = $('#jsp_admin_yhgl_datagrid').datagrid('getChecked');
        var ids = "";
        if(rows.length==0){
            $.messager.confirm('提示','生成数据可能需要一段时间，请耐心等待！',function(r){
                if(r){
                    document.location = "${pageContext.request.contextPath}/user/excelExport?ids=quanbu";
                }
            });
        }else{
            for(var i=0;i<rows.length;i++){
                ids += rows[i].id+",";
            }
            document.location = "${pageContext.request.contextPath}/user/excelExport?ids="+ids;
        }
    }
    function uploadExcel(){
        $("#jsp_admin_yhgl_upload_excel").dialog("open");
    }
    function uploadFile(){
        var fileName= $('#uploadExcel').filebox('getValue');
        if(fileName==""){
            $.messager.alert('提示','请选择上传文件！','info');
        }else{
            var d1=/\.[^\.]+$/.exec(fileName);
            if(d1 == ".xls"){
                document.getElementById("questionTypesManage").action="${pageContext.request.contextPath}/user/uploadExcel";
                document.getElementById("questionTypesManage").submit();
                $.messager.alert('提示','操作成功！','info');
            }else{
                $.messager.alert('提示','请选择xls格式文件！','info');
                $('#uploadExcel').filebox('setValue','');
            }
        }
    }
    function modifyRole(){
        var rows = $('#jsp_admin_yhgl_datagrid').datagrid('getChecked');
        var ids = "";
        if(rows.length==0){
            $.messager.alert('提示','必须选择一条用户信息！');
        }else if(rows.length>1){
            $.messager.alert('提示','只能选择一条用户信息！');
        }else{
            $("#jsp_amdin_yhgl_userId").val(rows[0].id);
            $("#jsp_amdin_yhgl_userName").textbox('setValue',rows[0].name);
            $("#jsp_admin_yhgl_roleDialog").dialog('open');
        }

    }
</script>
<div id="jsp_admin_yhgl_layout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',title:'查询条件',border:false" style="height:100px;">
        <form id="jsp_admin_yhgl_searchForm">
            检测用户名称（可模糊查询）<input name="name"/>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
        </form>
    </div>
    <div data-options="region:'center',border:false," style="height:200px;">
        <table id="jsp_admin_yhgl_datagrid">

        </table>
    </div>
</div>
<div id="jsp_admin_yhgl_addDialog" data-options="closed:true,modal:true,
                        title:'添加用户',
                        buttons:[{
                        text:'增加',
                        iconCls:'iconCls',
                        handler:function(){
                            $('#jsp_amdin_yhgl_addForm').form('submit',{
                                url:'${pageContext.request.contextPath}/user/add',
                                success:function(r){
                                    var obj = jQuery.parseJSON(r);
                                    if(obj.result == 1){
                                        $('#jsp_admin_yhgl_datagrid').datagrid('insertRow',{
                                            index:0,
                                            row:obj.data
                                        });
                                        $('#jsp_admin_yhgl_addDialog').dialog('close');
                                    }
                                    if(obj.result == 2){
                                        $('#jsp_admin_yhgl_addDialog').dialog('close');
                                    }
                                    $.messager.show({
                                        title:'提示',
                                        msg:obj.message
                                    });
                                }
                            });
                        }
                    }]" class="easyui-dialog" style="width: 418px;height: 102px;">
    <form id="jsp_amdin_yhgl_addForm">
        <table>
            <tr>
                <th>登录名称</th>
                <td>
                    <input id="jsp_amdin_yhgl_addForm_name" name="name" class="easyui-validatebox" data-options="required:true"/>
                </td>
                <th>密码</th>
                <td><input id="jsp_amdin_yhgl_addForm_pwd" type="password" name="pwd" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="jsp_admin_yhgl_updateDialog" data-options="closed:true,modal:true,
                        title:'修改用户',
                        buttons:[{
                            text:'修改',
                            iconCls:'iconCls',
                            handler:function(){
                                $('#jsp_amdin_yhgl_updateForm').form('submit',{
                                    url:'${pageContext.request.contextPath}/user/update',
                                    success:function(r){
                                        var obj = jQuery.parseJSON(r);
                                        if(obj.result == 1){
                                            $('#jsp_admin_yhgl_datagrid').datagrid('load',{});
                                            $('#jsp_admin_yhgl_updateDialog').dialog('close');
                                        }
                                        if(obj.result == 2){
                                            $('#jsp_admin_yhgl_updateDialog').dialog('close');
                                        }
                                        $.messager.show({
                                            title:'提示',
                                            msg:obj.message
                                        });
                                    }
                                });
                            }
                         }]"
                     class="easyui-dialog" style="width: 418px;height: 102px;">
    <form id="jsp_amdin_yhgl_updateForm" method="POST">
        <table>
            <tr>
                <th>登录名称</th>
                <td>
                    <input type="hidden" id="jsp_amdin_yhgl_updateForm_id" name="id" value=""/>
                    <input id="jsp_amdin_yhgl_updateForm_name" name="name" class="easyui-validatebox" data-options="required:true"/>
                </td>
                <th>密码</th>
                <td><input id="jsp_amdin_yhgl_updateForm_pwd" type="password" name="pwd" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>
<div id="jsp_admin_yhgl_upload_excel" data-options="closed:true,modal:true" class="easyui-dialog" style="width: 418px;height: 102px;">
    <form id="questionTypesManage"  method="post" enctype="multipart/form-data">
       <nobr> 选择文件：　<input id="uploadExcel" name="uploadExcel" class="easyui-filebox" style="width:200px" data-options="prompt:'请选择文件...'">

        　　<a href="#" class="easyui-linkbutton" style="width:122px;float:right" onclick="uploadFile()"  >导入用户</a> 　　     　　　　　
       </nobr>
    </form>
</div>
<div id="jsp_admin_yhgl_roleDialog" style="width: 300px;height: 300px;" class="easyui-dialog"
     data-options="closed:true,modal:true,
    title:'设置权限',
    buttons:[{
        text:'设置',
        icon:'icon-large-shapes',
        handler:function(){
            $('#jsp_amdin_yhgl_roleForm').form('submit',{
                url:'${pageContext.request.contextPath}/user/role',
                success:function(r){
                    var obj = jQuery.parseJSON(r);
                    if(obj.result == 1){
                        $('#jsp_admin_yhgl_datagrid').datagrid('load',{});
                        $('#jsp_admin_yhgl_updateDialog').dialog('close');
                    }
                    if(obj.result == 2){
                        $('#jsp_admin_yhgl_updateDialog').dialog('close');
                    }
                    $.messager.show({
                        title:'提示',
                        msg:obj.message
                    });
                }
            });
        }
    }]">
    <form id="jsp_amdin_yhgl_roleForm" method="post">
        <table>
            <tr>
                <td>
                    用户名：
                </td>
                <td>
                    <input id="jsp_amdin_yhgl_userId" name="userId"  type="hidden"/>
                    <input id="jsp_amdin_yhgl_userName" name="userName" class="easyui-textbox"  style="width:100%" disabled/>
                </td>
            </tr>
            <tr>
                <td>
                    角色名：
                </td>
                <td>
                    <select class="easyui-combobox" name="roleId" style="width:150px"
                            data-options="
                                url:'${pageContext.request.contextPath}/role/getRole',
                                method:'get',
                                valueField:'id',
                                textField:'roleName',
                                panelHeight:'auto'" >
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
