<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
  <script src="${contentPath}/modules/aa/scripts/application.js"></script>
</head>

<body>
<!-- Content Header (Page header) -->
<div ng-app="minis.webapp.content">
  <section class="content-header">
    <ol class="breadcrumb">
      <li><a href="/index.html"><i class="fa fa-dashboard"></i>首頁</a></li>
      <li class="active"><spring:message code="ApplicationManage"/></li>
    </ol>
    <h1>
      <spring:message code="ApplicationManage"/><small><i><spring:message code="Message_ApplicationManage"/></i></small>
    </h1>
  </section>

  <!-- Main content -->
  <section class="content" ng-controller="ApplicationCtrl">
    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header">
            <button class="btn btn-success" ng-click="createApp()">
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
                <th style="width: 15%"><spring:message code="ApplicationId" /></th>
                <th style="width: 15%"><spring:message code="ApplicationName" /></th>
                <th><spring:message code="ShortDescription" /></th>
              </tr>
              <tr ng-repeat="application in applications">
                <td class="text-center">
                  <button class="btn btn-xs btn-primary" ng-click="updateApp(application)">
                    <i class="icon-fixed fa fa-edit"></i>
                  </button>&nbsp;
                  <button class="btn btn-xs btn-danger" ng-click="deleteApp(application)">
                    <i class="icon-fixed fa fa-remove"></i>
                  </button>
                </td>
                <td>{{application.id}}</td>
                <td>{{application.name}}</td>
                <td>{{application.description | abbreviate:30}}</td>
              </tr>
            </table>
          </div><!-- /.box-body -->
        </div><!-- /.box -->
      </div>
    </div><!-- /.row -->
  </section><!-- /.content -->
  
  <script type="text/ng-template" id="ApplicationCreateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title">
          <spring:message code="ApplicationManage"/>
        </h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
            <label for="applicationId" class="col-sm-3 control-label">
              <spring:message code="ApplicationId" />
            </label> 
            <div class="col-sm-8">
              <input class="form-control" id="applicationId" ng-model="application.id" />
              <p class="help-block">{{$validate.id.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="applicationName" class="col-sm-3 control-label"><spring:message code="ApplicationName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="applicationName" ng-model="application.name"/>
              <p class="help-block">{{$validate.name.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.path.invalid }">
            <label for="applicationPath" class="col-sm-3 control-label"><spring:message code="ApplicationPath" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="applicationPath" ng-model="application.path"/>
              <p class="help-block">{{$validate.path.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
            <label for="description" class="col-sm-3 control-label"><spring:message code="ApplicationDescription" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="description" ng-model="application.description" />
              <p class="help-block">{{$validate.description.errorMessages}}</p>
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-success" ng-click="createApp()">
            <spring:message code="Create" />
          </button>&nbsp;
          <button type="button" class="btn btn-default" ng-click="cancelEdit()">
            <spring:message code="Cancel" />
          </button>
        </div>
      </form>
    </div>
  </script>

  <script type="text/ng-template" id="ApplicationUpdateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title">
          <spring:message code="ApplicationManage"/>
        </h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
            <label for="applicationId" class="col-sm-3 control-label">
              <spring:message code="ApplicationId" />
            </label> 
            <div class="col-sm-8">
              <input class="form-control" id="applicationId" ng-model="application.id" />
              <p class="help-block">{{$validate.id.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="applicationName" class="col-sm-3 control-label"><spring:message code="ApplicationName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="applicationName" ng-model="application.name"/>
              <p class="help-block">{{$validate.name.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.path.invalid }">
            <label for="applicationPath" class="col-sm-3 control-label"><spring:message code="ApplicationPath" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="applicationPath" ng-model="application.path"/>
              <p class="help-block">{{$validate.path.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
            <label for="description" class="col-sm-3 control-label"><spring:message code="ApplicationDescription" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="description" ng-model="application.description" />
              <p class="help-block">{{$validate.description.errorMessages}}</p>
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-primary" ng-click="updateApp()">
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
