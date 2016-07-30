<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
  $("#jsp_admin_operation_datagrid").datagrid({
    url:'${pageContext.request.contextPath}/operation/datagrid',
    fit : true,
    fitColumns : true,
    border : false,
    pagination : true,
    idField : 'id',
    pageSize : 10,
    pageList : [ 10, 20, 30, 40, 50 ],
    sortName : 'id',
    sortOrder : 'desc',
    rownumbers:true,
    checkOnSelect : false,
    selectOnCheck : false,
    onClickRow:function(rowIndex, rowData){
      $('#jsp_admin_operation_datagrid').datagrid('unselectAll');
      $("#jsp_admin_operation_name").val("");
      $("#jsp_admin_operation_project_name").val("");
      $("#jsp_admin_operation_commom").val("");
      console.operation(rowData);
      var id = rowData.id;
      $.ajax({
        url : '${pageContext.request.contextPath}/operation/'+id+'/detail',
        type:'POST',
        dataType:'json',
        success:function(r){
          $("#jsp_admin_operation_name").val(r.name);
          $("#jsp_admin_operation_project_name").val(r.projectName);
          $("#jsp_admin_operation_commom").val(r.common);
        }
      });
    },
    frozenColumns:[[
      {
        field:'userName',
        title:'用户名',
        width:100
      },{
        field:'methodName',
        title:'请求方法',
        width:450
      },{
        field:'describeCommon',
        title:'方法描述',
        width:150
      },{
        field:'createDate',
        title:'访问时间',
        width:150
      }
    ]],
    toolbar:[
      {
        text:'刷新',
        iconCls:'icon-add',
        handler:function(){
          $('#jsp_admin_operation_datagrid').datagrid('load');
        }
      }
    ]
  });
</script>
<div id="jsp_admin_operation_layout" class="easyui-layout" data-options="fit:true,border:false">
  <div data-options="region:'center',border:false," style="height:200px;">
    <table id="jsp_admin_operation_datagrid">

    </table>
  </div>
</div>