
var webappContent = angular.module('minis.webapp.content', 
        [ 'ngAnimate', 'ui.bootstrap', 'minis.directives', 'kendo.directives' ]);

webappContent.controller('ControlitemPermissionCtrl', function($scope, $uibModal, $minis) {

    $scope.roleAutocomplateOptions = {
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
                    url : Minis.getBaseUrl("~/aa/autocomplate/role"),
                },
                parameterMap : function(data, operation) {
                    return kendo.stringify(data);
                }
            }
        }, 
        change: function() {
            $scope.role = this.dataItem();
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

    $scope.findControlitemPermissions = function() {

        if ($scope.role == undefined) {
            return;
        }

        $.blockUI();

        var url = "~/aa/controlitem/permission/findByRoleId/" + $scope.role.id;

        $minis.callService("GET", url)
        .then(function(response) {

            $scope.controlitems = response.data.controlitems;
            $scope.controlitemGroups = response.data.controlitemGroups;

        }, function(response) {

        }).finally(function() {
            $.unblockUI();
        });
    };

    $scope.saveControlitemPermissions = function() {

        $.blockUI();

        var selectedControlitemIds = [];
        var targetData = $("#targetGrid").data("kendoGrid").dataSource.data();

        $.each(targetData, function(index, data) {
            selectedControlitemIds.push(data.id);
        });

        var selectedControlitems = {
            controlitems : selectedControlitemIds
        };

        var url = "~/aa/controlitem/permission/updateByRoleId/" + $scope.role.id;
        $minis.callService("POST", url, selectedControlitems)
        .then(function(response) {

        }, function(response) {

        }).finally(function() {
            $.unblockUI();
        });
    };

    $scope.selectControlitems = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = sourceGrid.select();
        rows.each(function(index, row) {
            var selectedItem = sourceGrid.dataItem(row);
            sourceGrid.dataSource.remove(selectedItem);
            targetGrid.dataSource.add(selectedItem);
        });
    };

    $scope.unselectControlitems = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = targetGrid.select();
        rows.each(function(index, row) {
            var selectedItem = targetGrid.dataItem(row);
            targetGrid.dataSource.remove(selectedItem);
            sourceGrid.dataSource.add(selectedItem);
        });
    };

    $scope.selectAllControlitems = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = sourceGrid.dataSource.view();
        $.each(rows, function(index, row) {
            sourceGrid.dataSource.remove(row);
            targetGrid.dataSource.add(row);
        });
    };

    $scope.unselectAllControlitems = function() {

        var sourceGrid = $("#sourceGrid").data("kendoGrid");
        var targetGrid = $("#targetGrid").data("kendoGrid");

        var rows = targetGrid.dataSource.view();
        $.each(rows, function(index, row) {
            targetGrid.dataSource.remove(row);
            sourceGrid.dataSource.add(row);
        });
    };

    $("#sourceGrid").on("dblclick", "tr.k-state-selected", $scope.selectControlitems);
    $("#targetGrid").on("dblclick", "tr.k-state-selected", $scope.unselectControlitems);

});

