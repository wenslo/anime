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
                width:100
            }, {field:'name',
                title:'登录名称',
                width:100
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
                align:'right'
             }, {field:'modifydatetime',
                title:'最后修改时间',
                width:100,
                align:'right'}
        ]]
    });
</script>
<table id="jsp_admin_yhgl_datagrid">

</table>
