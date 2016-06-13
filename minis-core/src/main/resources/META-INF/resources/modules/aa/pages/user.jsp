<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>
<head>
  <title><spring:message code="UserManage"/></title>
  <script src="${contentPath}/modules/aa/scripts/user.js"></script>
</head>

<body>
<!-- Content Header (Page header) -->
<div ng-app="minis.webapp.content">
  <section class="content-header">
    <ol class="breadcrumb">
      <li><a href="/index.html"><i class="fa fa-dashboard"></i>首頁</a></li>
      <li class="active"><spring:message code="UserManage"/></li>
    </ol>
    <h1>
      <spring:message code="UserManage"/><small><i><spring:message code="Message_UserManage"/></i></small>
    </h1>
  </section>

  <!-- Main content -->
  <section class="content" ng-controller="UserCtrl">
    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header">
            <sec:accesscontrollist hasPermission="ADD_USER" domainObject="">
              <button class="btn btn-success" ng-click="createUser()">
                <i class="fa fa-plus"></i>&nbsp;&nbsp;<spring:message code="Add" />
              </button>
            </sec:accesscontrollist>
            &nbsp;
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
                <th style="width: 15%"><spring:message code="Username" /></th>
                <th style="width: 15%"><spring:message code="FullName" /></th>
                <th style="width: 12%"><spring:message code="LocaleLanguage" /></th>
                <th style="width: 12%"><spring:message code="Enabled?" /></th>
                <th><spring:message code="Email" /></th>
              </tr>
              <tr ng-repeat="user in users">
                <td class="text-center">
                  <sec:accesscontrollist hasPermission="UPDATE_USER" domainObject="">
                  <button class="btn btn-xs btn-primary" ng-click="updateUser(user)">
                    <i class="icon-fixed fa fa-edit"></i>
                  </button>&nbsp;
                  </sec:accesscontrollist>
                  <sec:accesscontrollist hasPermission="DELETE_USER" domainObject="">
                  <button class="btn btn-xs btn-danger" ng-click="deleteUser(user)">
                    <i class="icon-fixed fa fa-remove"></i>
                  </button>
                  </sec:accesscontrollist>
                </td>
                <td>{{user.username}}</td>
                <td>{{user.name}}</td>
                <td>{{user.locale}}</td>
                <td>
                    <span ng-if="user.enabled == true" class="label bg-green">{{user.enabled}}</span>
                    <span ng-if="user.enabled == false" class="label bg-red">{{user.enabled}}</span>
                </td>
                <td>{{user.email}}</td>
              </tr>
            </table>
          </div><!-- /.box-body -->
        </div><!-- /.box -->
      </div>
    </div><!-- /.row -->
  </section><!-- /.content -->
  
  <script type="text/ng-template" id="UserCreateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title">
          <spring:message code="UserManage"/>
        </h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.username.invalid }" >
            <label for="username" class="col-sm-3 control-label"><spring:message code="Username"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="username" ng-model="user.username"/>
              <small class="help-block">{{$validate.username.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.password.invalid }" >
            <label for="password" class="col-sm-3 control-label"><spring:message code="UserPassword" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="password" ng-model="user.password" />
              <small class="help-block">{{$validate.password.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.email.invalid }" >
            <label for="email" class="col-sm-3 control-label"><spring:message code="Email" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="email" ng-model="user.email" />
              <small class="help-block">{{$validate.email.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }" >
            <label for="name" class="col-sm-3 control-label"><spring:message code="FullName"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="name" ng-model="user.name" />
              <small class="help-block">{{$validate.name.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.localName.invalid }" >
            <label for="localName" class="col-sm-3 control-label"><spring:message code="DisplayName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="localName" ng-model="user.localName" />
              <small class="help-block">{{$validate.localName.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.locale.invalid }" >
            <label for="locale" class="col-sm-3 control-label"><spring:message code="LocaleLanguage" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="locale" ng-model="user.locale" />
            </div>
            <small class="help-block">{{$validate.locale.errorMessages}}</small>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="UserStatus" /></label> 
            <div class="col-sm-8 checkbox">
                <label>
                  <input type="checkbox" ng-model="user.enabled" />
                  <spring:message code="Enabled?" />
                </label>&nbsp;&nbsp;
                <label>
                  <input type="checkbox" ng-model="user.locked" />
                  <spring:message code="Locked?" />
                </label>&nbsp;&nbsp;
                <label>
                  <input type="checkbox" ng-model="user.expired" />
                  <spring:message code="Expired?" />
                </label>&nbsp;&nbsp;
                <label>
                  <input type="checkbox" ng-model="user.passwordExpired" />
                  <spring:message code="PasswordExpired?" />
                </label>&nbsp;&nbsp;
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" id="createUserBtn" class="btn btn-success" ng-click="createUser()">
            <spring:message code="Add" />
          </button>&nbsp;
          <button type="button" id="cancelEditBtn" class="btn btn-default" ng-click="cancelEdit('userForm')">
            <spring:message code="Cancel" />
          </button>
        </div>
      </form>
    </div>
  </script>
  <script type="text/ng-template" id="UserUpdateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title"><spring:message code="UserManage"/></h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group">
            <label for="userId" class="col-sm-3 control-label"><spring:message code="UserId"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="userId" ng-model="user.id" readonly/>
              <small class="help-block">{{$validate.id.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.username.invalid }">
            <label for="username" class="col-sm-3 control-label"><spring:message code="Username"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="username" ng-model="user.username"/>
              <small class="help-block">{{$validate.username.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.password.invalid }">
            <label for="password" class="col-sm-3 control-label"><spring:message code="UserPassword"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="password" ng-model="user.password"/>
              <small class="help-block">{{$validate.password.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.email.invalid }">
            <label for="email" class="col-sm-3 control-label"><spring:message code="Email"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="email" ng-model="user.email" />
              <small class="help-block">{{$validate.email.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="name" class="col-sm-3 control-label"><spring:message code="FullName"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="name" ng-model="user.name"/>
              <small class="help-block">{{$validate.name.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.localName.invalid }">
            <label for="localName" class="col-sm-3 control-label"><spring:message code="DisplayName"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="localName" ng-model="user.localName"/>
              <small class="help-block">{{$validate.localName.errorMessages}}</small>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.locale.invalid }">
            <label for="locale" class="col-sm-3 control-label"><spring:message code="LocaleLanguage"/></label> 
            <div class="col-sm-8">
              <input class="form-control" id="locale" ng-model="user.locale"/>
            </div>
            <small class="help-block">{{$validate.locale.errorMessages}}</small>
          </div>
          <div class="form-group">
            <label class="col-sm-3 control-label"><spring:message code="UserStatus"/></label> 
            <div class="col-sm-8 checkbox">
                <label>
                  <input type="checkbox" ng-model="user.enabled" />
                  <spring:message code="Enabled?" />
                </label>&nbsp;&nbsp;
                <label>
                  <input type="checkbox" ng-model="user.locked" />
                  <spring:message code="Locked?" />
                </label>&nbsp;&nbsp;
                <label>
                  <input type="checkbox" ng-model="user.expired" />
                  <spring:message code="Expired?" />
                </label>&nbsp;&nbsp;
                <label>
                  <input type="checkbox" ng-model="user.passwordExpired" />
                  <spring:message code="PasswordExpired?" />
                </label>&nbsp;&nbsp;
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-primary" ng-click="updateUser()">
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
