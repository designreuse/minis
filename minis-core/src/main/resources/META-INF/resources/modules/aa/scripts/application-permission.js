
var webappContent = angular.module('minis.webapp.content', 
        [ 'ngAnimate', 'ui.bootstrap', 'minis.directives', 'kendo.directives' ]);

webappContent.controller('ApplicationPermissionCtrl', function($scope, $uibModal, $minis) {

    $scope.applicationAutoComplateOptions = {
        delay : 500,
        ignoreCase : true,
        filter : "contains",
        dataTextField : "id",
        dataSource : {
            type: "odata",
            serverFiltering : true,
            transport: {
                read : {
                    type : "POST",
                    dataType: "json",
                    contentType : "application/json",
                    url : Minis.getBaseUrl("~/aa/autocomplate/application"),
                },
                parameterMap : function(data, operation) {
                    return kendo.stringify(data);
                }
            }
        }, 
        change: function() {
            $scope.application = this.dataItem();
        },
    };

    $scope.sourceGridOptions = {
        height : 420,
        sortable: true,
        selectable: "multiple",
        columns : [ 
            { field : "id"         , title : "代碼", width : "30%" }, 
            { field : "name"       , title : "名稱", width : "30%" }, 
            { field : "description", title : "說明", width : "40%" } 
        ]
    };

    $scope.targetGridOptions = {
        height : 420,
        sortable: true,
        selectable: "multiple",
        columns : [ 
            { field : "id"         , title : "代碼", width : "30%" }, 
            { field : "name"       , title : "名稱", width : "30%" }, 
            { field : "description", title : "說明", width : "40%" } 
        ]
    };

    $scope.findAppPermissions = function() {

        if ($scope.application == undefined) {
            return;
        }

        $.blockUI();

        var url = "~/aa/application/permission/findByApplicationId/" + $scope.application.id;

        $minis.callService("GET", url)
        .then(function(response) {
            $scope.roles = response.data.roles;
            $scope.roleGroups = response.data.roleGroups;

        }, function(response) {


        }).finally(function() {
            $.unblockUI();
        });
    };

    $scope.saveAppPermissions = function() {

        $.blockUI();

        var targetData = $("#targetGrid").data("kendoGrid").dataSource.view();
        var selectedRoleIds = [];

        $.each(targetData, function(index, data) {
            selectedRoleIds.push(data.id);
        });

        var selectedRoleGroups = {
            roles : selectedRoleIds
        };

        var url = "~/aa/application/permission/updateByApplicationId/" + $scope.application.id;
        $minis.callService("POST", url, selectedRoleGroups)
        .then(function(response) {

        }, function(response) {

        }).finally(function() {
            $.unblockUI();
        });
    };

    $scope.selectRoles = function() {
        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");
        var rows = sourceGrid.select();
        rows.each(function(index, row) {
            var selectedItem = sourceGrid.dataItem(row);
            sourceGrid.dataSource.remove(selectedItem);
            targetGrid.dataSource.add(selectedItem);
        });
    };

    $scope.unselectRoles = function() {
        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");
        var rows = targetGrid.select();
        rows.each(function(index, row) {
            var selectedItem = targetGrid.dataItem(row);
            targetGrid.dataSource.remove(selectedItem);
            sourceGrid.dataSource.add(selectedItem);
        });
    };

    $scope.selectAllRoles = function() {
        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");
        var rows = sourceGrid.dataSource.view();
        $.each(rows, function(index, row) {
            sourceGrid.dataSource.remove(row);
            targetGrid.dataSource.add(row);
        });
    };

    $scope.unselectAllRoles = function() {
        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");
        var rows = targetGrid.dataSource.view();
        $.each(rows, function(index, row) {
            targetGrid.dataSource.remove(row);
            sourceGrid.dataSource.add(row);
        });
    };

    $("#sourceGrid").on("dblclick", "tr.k-state-selected", $scope.selectRoles);
    $("#targetGrid").on("dblclick", "tr.k-state-selected", $scope.unselectRoles);

});

