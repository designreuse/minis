
var webappContent = angular.module('minis.webapp.content', 
        [ 'ngAnimate', 'ui.bootstrap', 'minis.directives' ]);

webappContent.controller('UserCtrl', function($scope, $uibModal, $minis, $log) {

    $scope.page = 1;
    $scope.pageSize = 20;
    $scope.maxPageSize = 10;

    display();

    $scope.pageChanged = function() {
        display();
    };

    $scope.createUser = function() {
        var modalInstance = $uibModal.open({
            templateUrl : 'UserCreateForm.html',
            controller : 'UserFormCtrl',
            size: 'lg',
            resolve : {
                $parent : function() {
                    return {
                        action : 'create',
                        users  : $scope.users
                    };
                }
            }
        });
    };

    $scope.updateUser = function(user) {
        var modalInstance = $uibModal.open({
            templateUrl : 'UserUpdateForm.html',
            controller : 'UserFormCtrl',
            size: 'lg',
            resolve : {
                $parent : function() {
                    return {
                        action : 'update',
                        users  : $scope.users,
                        user   : user
                    };
                }
            }
        });
    };

    $scope.deleteUser = function(user) {
        
        $.blockUI();
        
        $minis.callService("POST", "~/aa/user/deleteUser", user)
        .then(function(response) {
            var index = $scope.users.indexOf(user);
            $scope.users.splice(index, 1);
            MessageBox.success("刪除使用者資料成功.");
        }, function(response) {
            MessageBox.success("刪除使用者資料失敗.");
        }).finally(function() {
            $.unblockUI();
        });
    };

    function display() {

        $.blockUI();

        var request = {
                page     : $scope.page,
                pageSize : $scope.pageSize
        };

        $minis.callService("POST", "~/aa/user/searchUser", request)
        .then(function(response) {
            $scope.total = response.data.total;
            $scope.users = response.data.contents;
        }, function(response) {

        }).finally(function() {
            $.unblockUI();
        });
    }
});

webappContent.controller("UserFormCtrl", function($parent, $scope, $uibModalInstance, $minis) {

    // Initialize modal data.
    $scope.action = $parent.action;
    $scope.users = $parent.users;
    $scope.user = angular.copy($parent.user) || {};

    // Binding event.
    $scope.createUser = function() {
        
        $minis.callService("POST", "~/aa/user/createUser", $scope.user)
        .then(function(response) {
            $scope.users.splice(0, 0, response.data);
            MessageBox.success("新增使用者資料成功.");
            $uibModalInstance.close();

        }, function(response) {
            $minis.bindErrorMessages($scope, response);
            if (response.status === 409) {
                MessageBox.error("新增使用者資料失敗, 使用者資料重複.");
            }
        });
    };

    $scope.updateUser = function() {

        $minis.callService("POST", "~/aa/user/updateUser", $scope.user)
        .then(function(response) {
            var index = $scope.users.indexOf($parent.user);
            $scope.users.splice(index, 1, $scope.user);
            MessageBox.success("修改使用者資料成功.");
            $uibModalInstance.close();

        }, function(response) {
            $minis.bindErrorMessages($scope, response);
            if (response.status === 409) {
                MessageBox.error("修改使用者資料失敗, 使用者資料重複.");
            }
            if (response.status === 404) {
                MessageBox.error("找不到使用者資料, 該使用者可能已被刪除.");
            }
        });
    };

    $scope.cancelEdit = function() {
        $uibModalInstance.dismiss('cancel');
    };

});
