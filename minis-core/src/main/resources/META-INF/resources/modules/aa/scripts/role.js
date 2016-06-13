
var webappContent = angular.module('minis.webapp.content', 
		[ 'ngAnimate', 'ui.bootstrap', 'minis.directives' ]);

webappContent.controller('RoleCtrl', function($scope, $uibModal, $minis, $log) {

	$scope.page = 1;
	$scope.pageSize = 20;
	$scope.maxPageSize = 10;

	display();

	$scope.pageChanged = function() {
		display();
	};

	$scope.createRole = function() {
		var modalInstance = $uibModal.open({
			templateUrl : 'RoleCreateForm.html',
			controller : 'RoleFormCtrl',
			size: 'lg',
			resolve : {
				$parent : function() {
					return {
						action : 'create',
						roles  : $scope.roles
					};
				}
			}
		});
	};

	$scope.updateRole = function(role) {
		var modalInstance = $uibModal.open({
			templateUrl : 'RoleUpdateForm.html',
			controller : 'RoleFormCtrl',
			size: 'lg',
			resolve : {
				$parent : function() {
					return {
						action : 'update',
						roles  : $scope.roles,
						role   : role
					};
				}
			}
		});
	};

	$scope.deleteRole = function(role) {
		$minis.callService("POST", "~/aa/role/deleteRole", role)
		.then(function(response) {
			var index = $scope.roles.indexOf(role);
			$scope.roles.splice(index, 1);
			MessageBox.success("刪除角色資料成功.");

		}, function(response) {
			MessageBox.success("刪除角色資料失敗.");
		});
	};

	function display() {

		var request = {
				page     : $scope.page,
				pageSize : $scope.pageSize
		};

		$minis.callService("POST", "~/aa/role/searchRole", request)
		.then(function(response) {
			$scope.total = response.data.total;
			$scope.roles = response.data.contents;
		}, function(response) {

		});
	}
});

webappContent.controller("RoleFormCtrl", function($parent, $scope, $uibModalInstance, $minis) {

	// Initialize modal data.
	$scope.action = $parent.action;
	$scope.roles = $parent.roles;
	$scope.role = angular.copy($parent.role) || {};
	
	// Binding event.
	$scope.createRole = function() {
		$minis.callService("POST", "~/aa/role/createRole", $scope.role)
		.then(function(response) {
			$scope.roles.splice(0, 0, response.data);
			MessageBox.success("新增角色資料成功.");
			$uibModalInstance.close();

		}, function(response) {
			$minis.bindErrorMessages($scope, response);
			if (response.status === 409) {
				MessageBox.error("新增角色資料失敗, 角色資料重複.");
			}
		});
	};

	$scope.updateRole = function() {
		$minis.callService("POST", "~/aa/role/updateRole", $scope.role)
		.then(function(response) {
            if ($parent.role.id === $scope.role.id) {
                var index = $scope.roles.indexOf($parent.role);
                $scope.roles.splice(index, 1, $scope.role);
                MessageBox.success("修改角色資料成功.");
            } else {
                $scope.roles.splice(0, 0, response.data);
                MessageBox.success("新增角色資料成功.");
            }
            $uibModalInstance.close();

		}, function(response) {
			$minis.bindErrorMessages($scope, response);
		});
	};

	$scope.cancelEdit = function() {
		$uibModalInstance.dismiss('cancel');
	};

});
