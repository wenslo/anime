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

  <jsp:include page="user/login.jsp"></jsp:include>

  <jsp:include page="user/reg.jsp"></jsp:include>
</body>
</html>
