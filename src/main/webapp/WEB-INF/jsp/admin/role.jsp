<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
  $("#jsp_admin_role_datagrid").datagrid({
    url:'${pageContext.request.contextPath}/role/datagrid',
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
    onClickRow:function(rowIndex, rowData){
        $('#jsp_admin_role_datagrid').datagrid('unselectAll');
        $("#jsp_admin_role_name").val("");
        $("#jsp_admin_role_project_name").val("");
        $("#jsp_admin_role_commom").val("");
        console.role(rowData);
        var id = rowData.id;
        $.ajax({
            url : '${pageContext.request.contextPath}/role/'+id+'/detail',
            type:'POST',
            dataType:'json',
            success:function(r){
                $("#jsp_admin_role_name").val(r.name);
                $("#jsp_admin_role_project_name").val(r.projectName);
                $("#jsp_admin_role_commom").val(r.common);
            }
        });
    },
    frozenColumns:[[
      {
        field:'id',
        title:'编号',
        width:50,
        checkbox : true,
        sortable:true
      },{
            field:'roleNumber',
            title:'角色编号',
            width:100
      },{
        field:'roleName',
        title:'角色名',
        width:100
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
        text:'删除',
        iconCls:'icon-edit',
        handler:function(){
            remove();
        }
      },'-',{
        text:'导出',
        iconCls:'icon-more',
        handler:function(){
            excelExport();
        }
      }
    ],
  });
  function append(){
    $('#jsp_amdin_role_addForm input').val('');
    $("#jsp_admin_role_addDiarole").dialog('open');
  }
  function remove(){
      var rows = $('#jsp_admin_role_datagrid').datagrid('getChecked');
      var ids = [];
      if(rows.length>0){
          $.messager.confirm('确认','您是否要删除当前选中的角色？',function(r){
              if(r){
                  for(var i=0;i<rows.length;i++){
                      ids.push([rows[i].id]);
                  }
                  $.ajax({
                      url : '${pageContext.request.contextPath}/role/delete',
                      type:'POST',
                      data:{
                          ids:ids.join(',')
                      },
                      dataType:'json',
                      success:function(r){
                          $('#jsp_admin_role_datagrid').datagrid('load');
                          $('#jsp_admin_role_datagrid').datagrid('unselectAll');
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
              msg:'请选择要删除的角色！'
          });
      }
  }
  function excelExport(){
      var rows = $('#jsp_admin_role_datagrid').datagrid('getChecked');
      var ids = "";
      if(rows.length==0){
          $.messager.confirm('提示','生成数据可能需要一段时间，请耐心等待！',function(r){
              if(r){
                  document.location = "${pageContext.request.contextPath}/role/excelExport?ids=quanbu";
              }
          });
      }else{
          for(var i=0;i<rows.length;i++){
              ids += rows[i].id+",";
          }
          document.location = "${pageContext.request.contextPath}/role/excelExport?ids="+ids;
      }
  }
  function searchFun(){
    $("#jsp_admin_role_datagrid").datagrid('load',serializeObject($('#jsp_admin_role_searchForm')));
  }
  function checkNumber(){
      var roleNumber = $("#jsp_admin_role_roleNumber").val();
      $.ajax({
          url : '${pageContext.request.contextPath}/role/'+roleNumber+'/checkNumber',
          type:'POST',
          dataType:'json',
          success:function(r){
              var n = r.result;
              if(n==2){
                  $.messager.alert("提示","对不起，该角色数字已经存在")
                  $("#jsp_admin_role_addDiarole").datagrid("close");
              }
              if(n==3){
                  $.messager.alert("提示","对不起，角色数字必须递增");
                  $("#jsp_admin_role_addDiarole").dialog("close");
              }
          }
      });
  }
</script>
<div id="jsp_admin_role_layout" class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'center',border:false," style="height:200px;">
    <table id="jsp_admin_role_datagrid">

    </table>
  </div>
</div>
<div id="jsp_admin_role_addDiarole"  data-options="closed:true,modal:true,
                        title:'添加角色',
                        buttons:[{
                        text:'增加',
                        iconCls:'iconCls',
                        handler:function(){
                            $('#jsp_amdin_role_addForm').form('submit',{
                                url:'${pageContext.request.contextPath}/role/add',
                                success:function(r){
                                    var obj = jQuery.parseJSON(r);
                                    console.log(obj);
                                    if(obj.result == 1){
                                        $('#jsp_admin_role_datagrid').datagrid('insertRow',{
                                            index:0,
                                            row:obj.data
                                        });
                                        $('#jsp_admin_role_addDiarole').dialog('close');
                                    }
                                    if(obj.result == 2){
                                        $('#jsp_admin_role_addDiarole').dialog('close');
                                    }
                                    $.messager.show({
                                        title:'提示',
                                        msg:obj.message
                                    });
                                }
                            });
                        }
                    }]" class="easyui-dialog" style="width: 450px;height: 100px;">
  <form id="jsp_amdin_role_addForm">
    <table>
        <tr>
            <th>角色编号</th>
            <td><input id="jsp_admin_role_roleNumber" name="roleNumber" onblur="checkNumber();" class="easyui-validatebox"  data-options="required:true"/></td>
            <th>角色名称</th>
            <td><input id="jsp_admin_role_roleName" name="roleName" class="easyui-validatebox" data-options="required:true"/></td>
        </tr>
    </table>
  </form>
</div>