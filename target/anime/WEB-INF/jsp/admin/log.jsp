<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
  $("#jsp_admin_log_datagrid").datagrid({
    url:'${pageContext.request.contextPath}/log/datagrid',
    fit : true,
    fitColumns : true,
    border : false,
    pagination : true,
    idField : 'id',
    pageSize : 10,
    pageList : [ 10, 20, 30, 40, 50 ],
    sortName : 'id',
    sortOrder : 'desc',
    checkOnSelect : false,
    selectOnCheck : false,
    frozenColumns:[[
      {
        field:'id',
        title:'编号',
        width:50,
        checkbox : true,
        sortable:true
      },{
        field:'name',
        title:'姓名',
        width:100
      }
    ]],
    columns:[[
      {
        field:'common',
        title:'内容',
        width:150
      },{
        field:'projectName',
        title:'所属项目',
        width:150
      },{
        field:'createDate',
        title:'创建时间',
        width:150
      },{
        field:'updateDate',
        title:'修改时间',
        width:150
      },{
        field:'state',
        title:'完成状态',
        width:50,
        formatter : function(value, row, index) {
          if(value == "0"){
            return "好";
          }
          if(value == "1"){
            return "完成";
          }
          if(value == "2"){
            return "差";
          }
          if(value == "3"){
            return "未完成";
          }
          if(value == "4"){
            return "未审核";
          }
        }
      }
    ]],
    toolbar:[
      {
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
          append();
        }
      },'-',{
        text:'修改',
        iconCls:'icon-edit',
        handler:function(){
        }
      },'-',{
        text:'删除',
        iconCls:'icon-edit',
        handler:function(){
        }
      },'-',{
        text:'导出',
        iconCls:'icon-more',
        handler:function(){
        }
      }
    ],
  });
  function append(){
    $('#jsp_amdin_log_addForm input').val('');
    $("#jsp_admin_log_addDialog").dialog('open');
  }
  function searchFun(){
    $("#jsp_admin_log_datagrid").datagrid('load',serializeObject($('#jsp_admin_log_searchForm')));
  }
</script>
<div id="jsp_admin_log_layout" class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'north',title:'查询条件',border:false" style="height:100px;">
    <form id="jsp_admin_log_searchForm">
      检测内容（可模糊查询）<input name="common"/>
      <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
      <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
    </form>
  </div>
  <div data-options="region:'center',border:false," style="height:200px;">
    <table id="jsp_admin_log_datagrid">

    </table>
  </div>
</div>
<div id="jsp_admin_log_addDialog" data-options="closed:true,modal:true,
                        title:'添加日志',
                        buttons:[{
                        text:'增加',
                        iconCls:'iconCls',
                        handler:function(){
                            $('#jsp_amdin_log_addForm').form('submit',{
                                url:'${pageContext.request.contextPath}/log/add',
                                success:function(r){
                                    var obj = jQuery.parseJSON(r);
                                    console.log(obj);
                                    if(obj.result == 1){
                                        $('#jsp_admin_log_datagrid').datagrid('insertRow',{
                                            index:0,
                                            row:obj.data
                                        });
                                        $('#jsp_admin_log_addDialog').dialog('close');
                                    }
                                    if(obj.result == 2){
                                        $('#jsp_admin_log_addDialog').dialog('close');
                                    }
                                    $.messager.show({
                                        title:'提示',
                                        msg:obj.message
                                    });
                                }
                            });
                        }
                    }]" class="easyui-dialog" style="width: 600px;height: 250px;">
  <form id="jsp_amdin_log_addForm">
    <table>
      <tr>
        <th style="background: #0n0n0n;">所属项目</th>
        <td>
          <select class="easyui-combobox" name="projectName" style="width:200px;"
                  data-options="
                url:'${pageContext.request.contextPath}/project/getProject',
                method:'get',
                valueField:'id',
                textField:'projectName',
                panelHeight:'auto'" >

          </select>
        </td>
      </tr>
      <tr>
        <th>创建时间</th>
        <td>
            <input class="easyui-datetimebox" name="createDate" required style="width:200px">
        </td>
      </tr>
      <tr>
        <th style="background: #0n0n0n;">日志内容</th>
        <td>
          <textarea id="jsp_admin_log_addFrom_commom" name="common" class="textarea easyui-validatebox" data-options="required:true" style="width: 500px;"></textarea>
          <%--<input id="jsp_amdin_log_addForm_common" name="common" class="easyui-validatebox" data-options="required:true" style="height: 200px;width:500px"/>--%>
        </td>
      </tr>
    </table>
  </form>
</div>