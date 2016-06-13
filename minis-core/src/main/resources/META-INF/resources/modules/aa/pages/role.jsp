<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<head>
  <script src="${contentPath}/modules/aa/scripts/role.js"></script>
</head>

<body>
<!-- Content Header (Page header) -->
<div ng-app="minis.webapp.content">
  <section class="content-header">
    <ol class="breadcrumb">
      <li><a href="/index.html"><i class="fa fa-dashboard"></i>首頁</a></li>
      <li class="active"><spring:message code="RoleManage"/></li>
    </ol>
    <h1>
      <spring:message code="RoleManage"/><small><i><spring:message code="Message_RoleManage"/></i></small>
    </h1>
  </section>

  <!-- Main content -->
  <section class="content" ng-controller="RoleCtrl">
    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header">
            <button class="btn btn-success" ng-click="createRole()">
              <i class="fa fa-plus"></i>&nbsp;<spring:message code="Add" />
            </button>
            <div class="box-tools">
              <uib-pagination class="pagination pagination-sm no-margin pull-right" 
                ng-model="page" total-items="total" items-per-page="pageSize" max-size="maxPageSize"  
                boundary-links="true" ng-change="pageChanged()"
                previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
              </uib-pagination>
            </div>
          </div><!-- /.box-header -->
  
          <div class="box-body table-responsive no-padding">
            <table class="table table-hover">
              <tr>
                <th style="width: 100px"></th>
                <th style="width: 15%"><spring:message code="RoleId" /></th>
                <th style="width: 15%"><spring:message code="RoleName" /></th>
                <th><spring:message code="RoleDescription" /></th>
              </tr>
              <tr ng-repeat="role in roles">
                <td class="text-center">
                  <button class="btn btn-xs btn-primary" ng-click="updateRole(role)">
                    <i class="icon-fixed fa fa-edit"></i>
                  </button>&nbsp;
                  <button class="btn btn-xs btn-danger" ng-click="deleteRole(role)">
                    <i class="icon-fixed fa fa-remove"></i>
                  </button>
                </td>
                <td>{{role.id}}</td>
                <td>{{role.name}}</td>
                <td>{{role.description}}</td>
              </tr>
            </table>
          </div><!-- /.box-body -->
        </div><!-- /.box -->
      </div>
    </div><!-- /.row -->
  </section><!-- /.content -->
  
  <script type="text/ng-template" id="RoleCreateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title"><spring:message code="RoleManage"/></h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
            <label for="roleId" class="col-sm-3 control-label"><spring:message code="RoleId" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="roleId" ng-model="role.id" />
              <p class="help-block">{{$validate.id.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="roleName" class="col-sm-3 control-label"><spring:message code="RoleName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="roleName" ng-model="role.name"/>
              <p class="help-block">{{$validate.name.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
            <label for="description" class="col-sm-3 control-label"><spring:message code="RoleDescription" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="description" ng-model="role.description" />
              <p class="help-block">{{$validate.description.errorMessages}}</p>
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-success" ng-click="createRole()">
            <spring:message code="Create" />
          </button>&nbsp;
          <button type="button" class="btn btn-default" ng-click="cancelEdit()">
            <spring:message code="Cancel" />
          </button>
        </div>
      </form>
    </div>
  </script>
  
  <script type="text/ng-template" id="RoleUpdateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title">
          <spring:message code="RoleManage"/>
        </h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
            <label for="roleId" class="col-sm-3 control-label"><spring:message code="RoleId" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="roleId" ng-model="role.id" />
              <p class="help-block">{{$validate.id.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="roleName" class="col-sm-3 control-label"><spring:message code="RoleName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="roleName" ng-model="role.name"/>
              <p class="help-block">{{$validate.name.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
            <label for="description" class="col-sm-3 control-label"><spring:message code="RoleDescription" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="description" ng-model="role.description" />
              <p class="help-block">{{$validate.description.errorMessages}}</p>
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-primary" ng-click="updateRole()">
            <spring:message code="Update" />
          </button>&nbsp;
          <button type="button" class="btn btn-default" ng-click="cancelEdit()">
            <spring:message code="Cancel" />
          </button>
        </div>
      </form>
    </div>
  </script>
</div><!-- /.ng-app="minis.webapp.content" -->
</body>
</html>
