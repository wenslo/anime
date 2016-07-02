<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                formater:function(value,row,index){
                    return '<span title="'+row.name+value+'">'+value+'</span>';
                }
            }, {field:'createdatetime',
                title:'创建时间',
                width:150,
                align:'right',
            sortable:true
             }, {field:'modifydatetime',
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
    function remove(){
        var rows = $('#jsp_admin_yhgl_datagrid').datagrid('getChecked');
        var ids = [];
        alert(rows.length);
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
<div id="jsp_admin_yhgl_addDialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
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
                <td><input name="name" class="easyui-validatebox" data-options="required:true"/></td>
                <th>密码</th>
                <td><input type="password" name="pwd" class="easyui-validatebox" data-options="required:true"/></td>
            </tr>
        </table>
    </form>
</div>