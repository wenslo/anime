<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>简单的用户管理系统</title>
  <link rel="stylesheet" type="text/css" href="/anime/resource/easyui/themes/default/easyui.css">
  <link rel="stylesheet" type="text/css" href="/anime/resource/easyui/themes/icon.css">
  <link rel="stylesheet" type="text/css" href="/anime/resource/easyui/demo.css">
  <script type="text/javascript" src="/anime/resource/easyui/jquery.min.js"></script>
  <script type="text/javascript" src="/anime/resource/easyui/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="/anime/resource/easyui/locale/easyui-lang-zh_CN.js"></script>
  <script type="text/javascript" src="/anime/resource/js/whlUtil.js"></script>

</head>
<body class="easyui-layout">
  <div data-options="region:'north'" style="height:60px; background: #4b72a4">
    <span style="font-size: 20px;font-family: cursive;float: right;margin-top: 20px;color: #ffffff">欢迎${user.name}访问使用该系统！</span>
  </div>
  <div data-options="region:'south'" style="height:20px;"></div>
  <div data-options="region:'west',split:true" style="width:200px;">
    <jsp:include page="layout/west.jsp"/>
  </div>
  <div data-options="region:'east'" style="width:200px;">
    <jsp:include page="layout/east.jsp"/>
  </div>
  <div data-options="region:'center',title:'欢迎使用温海林的日志管理系统'">
    <jsp:include page="layout/center.jsp"/>
  </div>

  <jsp:include page="user/login.jsp"></jsp:include>

  <jsp:include page="user/reg.jsp"></jsp:include>
</body>
</html>
