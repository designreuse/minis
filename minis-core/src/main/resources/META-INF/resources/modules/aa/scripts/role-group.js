
var webappContent = angular.module('minis.webapp.content', 
        [ 'ngAnimate', 'ui.bootstrap', 'minis.directives', 'kendo.directives' ]);

webappContent.controller('RoleGroupCtrl', function($scope, $uibModal, $minis) {

    $scope.userAutocomplateOptions = {
        delay : 500,
        ignoreCase : true,
        filter : "contains",
        dataTextField : "username",
        dataSource : {
            type: "odata",
            serverFiltering : true,
            transport: {
                read : {
                    type : "POST",
                    dataType: "json",
                    contentType : "application/json",
                    url : Minis.getBaseUrl("~/aa/autocomplate/user"),
                },
                parameterMap : function(data, operation) {
                    return kendo.stringify(data);
                }
            }
        }, 
        change: function() {
            $scope.user = this.dataItem();
        },
    };

    $scope.sourceGridOptions = {
        height : 450,
        sortable: true,
        selectable: "multiple",
        columns : [ 
           { field : "id"         , title : "代碼", width : "30%" }, 
           { field : "name"       , title : "名稱", width : "30%" }, 
           { field : "description", title : "說明", width : "40%" } 
       ]
    };

    $scope.targetGridOptions = {
        height : 450,
        sortable: true,
        selectable: "multiple",
        columns : [ 
           { field : "id"         , title : "代碼", width : "30%" }, 
           { field : "name"       , title : "名稱", width : "30%" }, 
           { field : "description", title : "說明", width : "40%" } 
       ]
    };

    $scope.findRoleGroups = function() {

        if ($scope.user == undefined) {
            return;
        }

        $.blockUI();

        var url = "~/aa/role/group/findByUsername/" + $scope.user.username;

        $minis.callService("GET", url)
        .then(function(response) {
            $scope.roles = response.data.roles;
            $scope.roleGroups = response.data.roleGroups;

        }, function(response) {

        }).finally(function() {
            $.unblockUI();
        });
    };

    $scope.saveRoleGroups = function() {

        $.blockUI();

        var selectedRoleIds = [];
        var targetData = $("#targetGrid").data("kendoGrid").dataSource.data();

        $.each(targetData, function(index, data) {
            selectedRoleIds.push(data.id);
        });

        var selectRoleGroups = {
            roles : selectedRoleIds
        };

        var url = "~/aa/role/group/updateByUsername/" + $scope.user.username;
        $minis.callService("POST", url, selectRoleGroups)
        .then(function(response) {

        }, function(response) {

        }).finally(function() {
            $.unblockUI();
        });
    };

    $scope.selectRoleGroups = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = sourceGrid.select();
        rows.each(function(index, row) {
            var selectedItem = sourceGrid.dataItem(row);
            sourceGrid.dataSource.remove(selectedItem);
            targetGrid.dataSource.add(selectedItem);
        });
    };

    $scope.unselectRoleGroups = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = targetGrid.select();
        rows.each(function(index, row) {
            var selectedItem = targetGrid.dataItem(row);
            targetGrid.dataSource.remove(selectedItem);
            sourceGrid.dataSource.add(selectedItem);
        });
    };

    $scope.selectAllRoleGroups = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = sourceGrid.dataSource.view();
        $.each(rows, function(index, row) {
            sourceGrid.dataSource.remove(row);
            targetGrid.dataSource.add(row);
        });
    };

    $scope.unselectAllRoleGroups = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = targetGrid.dataSource.view();
        $.each(rows, function(index, row) {
            targetGrid.dataSource.remove(row);
            sourceGrid.dataSource.add(row);
        });
    };

    $("#sourceGrid").on("dblclick", "tr.k-state-selected", $scope.selectRoleGroups);
    $("#targetGrid").on("dblclick", "tr.k-state-selected", $scope.unselectRoleGroups);

});

