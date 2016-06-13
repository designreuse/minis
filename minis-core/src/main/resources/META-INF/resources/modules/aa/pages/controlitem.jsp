<%@ page language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
  <script src="${contentPath}/modules/aa/scripts/controlitem.js"></script>
</head>

<body>
<!-- Content Header (Page header) -->
<div ng-app="minis.webapp.content">
  <section class="content-header">
    <ol class="breadcrumb">
      <li><a href="/index.html"><i class="fa fa-dashboard"></i>首頁</a></li>
      <li class="active"><spring:message code="ControlitemManage"/></li>
    </ol>
    <h1>
      <spring:message code="ControlitemManage"/><small><i><spring:message code="Message_ControlitemManage"/></i></small>
    </h1>
  </section>

  <!-- Main content -->
  <section class="content" ng-controller="ControlitemCtrl">
    <div class="row">
      <div class="col-xs-12">
        <div class="box">
          <div class="box-header">
            <button class="btn btn-success" ng-click="createCtrlitm()">
              <i class="fa fa-plus"></i>&nbsp;<spring:message code="Add" />
            </button>
            <div class="box-tools">
              <uib-pagination class="pagination pagination-sm no-margin pull-right" 
                ng-model="page" total-items="total" items-per-page="pageSize" max-size="maxPageSize"  
                boundary-links="true" ng-change="pageChanged()"
                previous-text="&lsaquo;" next-text="&rsaquo;" first-text="&laquo;" last-text="&raquo;">
              </uib-pagination>
            </div>
          </div>
  
          <div class="box-body table-responsive no-padding">
            <table class="table table-hover">
              <tr>
                <th style="width: 100px"></th>
                <th style="width: 15%"><spring:message code="ControlitemId" /></th>
                <th style="width: 15%"><spring:message code="ControlitemName" /></th>
                <th><spring:message code="ShortDescription" /></th>
              </tr>
              <tr ng-repeat="controlitem in controlitems">
                <td class="text-center">
                  <button class="btn btn-xs btn-primary" ng-click="updateCtrlitm(controlitem)">
                    <i class="icon-fixed fa fa-edit"></i>
                  </button>&nbsp;
                  <button class="btn btn-xs btn-danger" ng-click="deleteCtrlitm(controlitem)">
                    <i class="icon-fixed fa fa-remove"></i>
                  </button>
                </td>
                <td>{{controlitem.id}}</td>
                <td>{{controlitem.name}}</td>
                <td>{{controlitem.description | abbreviate:30}}</td>
              </tr>
            </table>
          </div><!-- /.box-body -->
        </div><!-- /.box -->
      </div>
    </div><!-- /.row -->
  </section><!-- /.content -->

  <script type="text/ng-template" id="ControlitemCreateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title">
          <spring:message code="ControlitemManage"/>
        </h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
            <label for="controlitemId" class="col-sm-3 control-label"><spring:message code="ControlitemId" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="controlitemId" ng-model="controlitem.id" />
              <p class="help-block">{{$validate.id.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="controlitemName" class="col-sm-3 control-label"><spring:message code="ControlitemName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="controlitemName" ng-model="controlitem.name"/>
              <p class="help-block">{{$validate.name.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
            <label for="description" class="col-sm-3 control-label"><spring:message code="ControlitemDescription" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="description" ng-model="controlitem.description" />
              <p class="help-block">{{$validate.description.errorMessages}}</p>
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-success" ng-click="createCtrlitm()">
            <spring:message code="Create" />
          </button>&nbsp;
          <button type="button" class="btn btn-default" ng-click="cancelEdit()">
            <spring:message code="Cancel" />
          </button>
        </div>
      </form>
    </div>
  </script>

  <script type="text/ng-template" id="ControlitemUpdateForm.html">
    <div class="box box-primary">
      <div class="box-header with-border">
        <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
          <span aria-hidden="true">&times;</span>
        </button>
        <h3 class="box-title">
          <spring:message code="ControlitemManage"/>
        </h3>
      </div>
      <form class="form-horizontal">
        <div class="box-body">
          <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
            <label for="controlitemId" class="col-sm-3 control-label"><spring:message code="ControlitemId" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="controlitemId" ng-model="controlitem.id" />
              <p class="help-block">{{$validate.id.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
            <label for="controlitemName" class="col-sm-3 control-label"><spring:message code="ControlitemName" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="controlitemName" ng-model="controlitem.name"/>
              <p class="help-block">{{$validate.name.errorMessages}}</p>
            </div>
          </div>
          <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
            <label for="description" class="col-sm-3 control-label"><spring:message code="ControlitemDescription" /></label> 
            <div class="col-sm-8">
              <input class="form-control" id="description" ng-model="controlitem.description" />
              <p class="help-block">{{$validate.description.errorMessages}}</p>
            </div>
          </div>
        </div>
        <div class="box-footer text-right">
          <button type="button" class="btn btn-primary" ng-click="updateCtrlitm()">
            <spring:message code="Update" />
          </button>&nbsp;
          <button type="button" class="btn btn-default" ng-click="cancelEdit()">
            <spring:message code="Cancel" />
          </button>
        </div>
      </form>
    </div>
  </script>

  
<script type="text/ng-template" id="ControlitemForm.html">
  <form class="form-horizontal">
    <div class="modal-header modal-primary">
      <button type="button" class="close" aria-label="Close" ng-click="cancelEdit()">
        <span aria-hidden="true">&times;</span>
      </button>
      <h4 class="modal-title"><spring:message code="ControlitemManage"/></h4>
    </div><!-- /.modal-header -->
    <div class="modal-body">
      <div class="box-body">
        <div class="form-group" ng-class="{ 'has-error': $validate.id.invalid }">
          <label for="controlitemId" class="col-sm-3 control-label"><spring:message code="ControlitemId" /></label> 
          <div class="col-sm-8">
            <input class="form-control" id="controlitemId" ng-model="controlitem.id" />
            <p class="help-block">{{$validate.id.errorMessages}}</p>
          </div>
        </div>
        <div class="form-group" ng-class="{ 'has-error': $validate.name.invalid }">
          <label for="controlitemName" class="col-sm-3 control-label"><spring:message code="ControlitemName" /></label> 
          <div class="col-sm-8">
            <input class="form-control" id="controlitemName" ng-model="controlitem.name"/>
            <p class="help-block">{{$validate.name.errorMessages}}</p>
          </div>
        </div>
        <div class="form-group" ng-class="{ 'has-error': $validate.description.invalid }" >
          <label for="description" class="col-sm-3 control-label"><spring:message code="ControlitemDescription" /></label> 
          <div class="col-sm-8">
            <input class="form-control" id="description" ng-model="controlitem.description" />
            <p class="help-block">{{$validate.description.errorMessages}}</p>
          </div>
        </div>
      </div><!-- /.box-body -->
    </div><!-- /.modal-body -->
    <div class="modal-footer">
      <button type="button" id="createCtrlBtn" class="btn btn-primary" style="display: none;" ng-click="createControlitem()">
        <spring:message code="Add" />
      </button>
      <button type="button" id="updateCtrlBtn" class="btn btn-primary" style="display: none;" ng-click="updateControlitem()">
        <spring:message code="Save" />
      </button>
      <button type="button" id="cancelEditBtn" class="btn btn-default" style="display: none;" ng-click="cancelEdit()">
        <spring:message code="Cancel" />
      </button>
    </div><!-- /.modal-footer -->
  </form>
</script>

</div><!-- /.ng-app="minis.webapp.content" -->
</body>
</html>
