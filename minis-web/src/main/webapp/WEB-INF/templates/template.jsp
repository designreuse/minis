<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="ContentPath" content="${contextPath}">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title><sitemesh:write property='title' /></title>
  
  <link rel="stylesheet" href="${contextPath}/assets/kendo/css/kendo.common-bootstrap.min.css">
  <link rel="stylesheet" href="${contextPath}/assets/kendo/css/kendo.bootstrap.min.css">
  <link rel="stylesheet" href="${contextPath}/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${contextPath}/assets/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="${contextPath}/assets/themify-icons/themify-icons.css">
  <link rel="stylesheet" href="${contextPath}/assets/ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="${contextPath}/assets/AdminLTE/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${contextPath}/assets/AdminLTE/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${contextPath}/css/default.css">
  
  <script src="${contextPath}/assets/jquery/jquery-1.10.2.min.js"></script>
  <script src="${contextPath}/assets/jquery/plugin/jquery.blockUI.2.70.0.js"></script>
  <script src="${contextPath}/assets/noty/jquery.noty.min.js"></script>
  <script src="${contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

  <script src="${contextPath}/assets/angular/angular.js"></script>
  <script src="${contextPath}/assets/angular/angular-animate.js"></script>
  <script src="${contextPath}/assets/angular/ui-bootstrap-tpls-1.2.1.js"></script>

  <script src="${contextPath}/assets/kendo/js/kendo.all.min.js"></script>
  <script src="${contextPath}/assets/kendo/js/kendo.angular.min.js"></script>
  <script src="${contextPath}/assets/fastclick/fastclick.min.js"></script>
  <script src="${contextPath}/assets/AdminLTE/js/app.min.js"></script>
  <script src="${contextPath}/assets/AdminLTE/js/demo.js"></script>
  <script src="${contextPath}/assets/minis/js/minis-core.js"></script>
  <script src="${contextPath}/assets/minis/js/minis-angular.js"></script>
  <sitemesh:write property='head' />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@ include file="template-header.jsp"  %>
<%@ include file="template-sidebar.jsp" %>
<%@ include file="template-content.jsp" %>
<%@ include file="template-footer.jsp"  %>
<%@ include file="template-control-sidebar.jsp" %>
</div><!-- ./wrapper -->
</body>
</html>
