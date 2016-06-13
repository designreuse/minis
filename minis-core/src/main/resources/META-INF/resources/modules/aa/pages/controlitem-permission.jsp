<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
  <script src="${contextPath}/modules/aa/scripts/controlitem-permission.js"></script>
</head>

<body>
<!-- Content Header (Page header) -->
<div ng-app="minis.webapp.content">
  <section class="content-header">
    <ol class="breadcrumb">
      <li><a href="/index.html"><i class="fa fa-dashboard"></i>首頁</a></li>
      <li class="active"><spring:message code="ControlitemPermissionManage"/></li>
    </ol>
    <h1>
      <spring:message code="ControlitemPermissionManage"/>
      <small><i><spring:message code="Message_ControlitemPermissionManage"/></i></small>
    </h1>
  </section>

  <!-- Main content -->
  <section class="content" ng-controller="ControlitemPermissionCtrl">
    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header with-border">
            <button class="btn btn-primary" ng-click="findControlitemPermissions()">
              <i class="fa fa-save">&nbsp;&nbsp;<spring:message code="Find" /></i>
            </button>&nbsp;
            <button class="btn btn-primary" ng-click="saveControlitemPermissions()">
              <i class="fa fa-save">&nbsp;&nbsp;<spring:message code="Save" /></i>
            </button>
            <div class="box-tools">
            </div>
          </div><!-- /.box-header -->
          <form class="form-horizontal">
          <div class="box-body">
            <div class="col-md-4">
              <div class="form-group">
                <label for="controlitemId" class="col-md-5 control-label">
                  <spring:message code="ControlitemId"/>
                </label> 
                <div class="col-md-7">
                  <input class="form-control" id="controlitemId" ng-model="controlitem.id" 
                        kendo-auto-complete k-options="controlitemAutoComplateOptions" />
                </div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="form-group">
                <label for="controlitemName" class="col-md-5 control-label">
                  <spring:message code="ControlitemName"/>
                </label> 
                <div class="col-md-7">
                  <input class="form-control" id="controlitemName" ng-model="controlitem.name"/>
                </div>
              </div>
            </div>
          </div><!-- /.box-body -->
          <div class="box-footer">
            <div class="row">
              <div class="col-md-12 text-center" style="padding-bottom: 10px;">
                &nbsp;&nbsp;&nbsp;
                <button class="btn btn-default" ng-click="unselectAllRoles()">
                  <i class="icon-fixed fa fa-angle-double-left"></i>
                </button>&nbsp;&nbsp;
                <button class="btn btn-default" ng-click="unselectRoles()">
                  <i class="icon-fixed fa fa-angle-left"></i>
                </button>&nbsp;&nbsp;
                <button class="btn btn-default" ng-click="selectRoles()">
                  <i class="icon-fixed fa fa-angle-right"></i>
                </button>&nbsp;&nbsp;
                <button class="btn btn-default" ng-click="selectAllRoles()">
                  <i class="icon-fixed fa fa-angle-double-right"></i>
                </button>&nbsp;&nbsp;
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <kendo-grid id="sourceGrid" 
                    k-options="sourceGridOptions" k-data-source="roles"/>
              </div>
              <div class="col-md-6">
                <kendo-grid  id="targetGrid" 
                    k-options="targetGridOptions" k-data-source="roleGroups"/>
              </div>
            </div>
          </div><!-- /.box-footer -->
          </form>      
        </div><!-- /.box -->
      </div>
    </div><!-- /.row -->
  </section><!-- /.content -->
</div><!-- /.ng-app="minis.webapp.content" -->
</body>
</html>
