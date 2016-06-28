<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-panel" data-options="title:'功能导航',border:false,fit:true">
  <div id="aa" class="easyui-accordion" data-options='fit:true,border:false'>
    <div title="系统菜单" data-options="iconCls:'icon-save'">
      <ul class="easyui-tree" data-options="

            url : '${pageContext.request.contextPath}/menu/treeNode',
            parentField:'pid',
            lines:true,
            onClick:function(node){
              console.log(node);
              if(node.attributes.url){
                var url = '${pageContext.request.contextPath}'+node.attributes.url;
                addTab({title:node.text,href:url,closable:true});
              }
            }
            "></ul>
    </div>
    <div title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;margin:0 auto;">

    </div>
  </div>
</div>
