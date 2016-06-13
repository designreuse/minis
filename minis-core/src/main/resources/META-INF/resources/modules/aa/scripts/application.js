
var webappContent = angular.module('minis.webapp.content', 
		[ 'ngAnimate', 'ui.bootstrap', 'minis.directives' ]);

webappContent.controller('ApplicationCtrl', function($scope, $uibModal, $minis, $log) {

	$scope.page = 1;
	$scope.pageSize = 20;
	$scope.maxPageSize = 10;

	display();

	$scope.pageChanged = function() {
		display();
	};

	$scope.createApp = function() {
		var modalInstance = $uibModal.open({
			templateUrl : 'ApplicationCreateForm.html',
			controller : 'ApplicationFormCtrl',
			size: 'lg',
			resolve : {
				$parent : function() {
					return {
						action : 'create',
						applications  : $scope.applications
					};
				}
			}
		});
	};

	$scope.updateApp = function(application) {
		var modalInstance = $uibModal.open({
			templateUrl : 'ApplicationUpdateForm.html',
			controller : 'ApplicationFormCtrl',
			size: 'lg',
			resolve : {
				$parent : function() {
					return {
						action : 'update',
						applications  : $scope.applications,
						application   : application
					};
				}
			}
		});
	};

	$scope.deleteApp = function(application) {
		$minis.callService("POST", "~/aa/application/deleteApp", application)
		.then(function(response) {
			var index = $scope.applications.indexOf(application);
			$scope.applications.splice(index, 1);
			MessageBox.success("刪除應用程式資料成功.");

		}, function(response) {
			MessageBox.success("刪除應用程式資料失敗.");
		});
	};

	function display() {

		var request = {
				page     : $scope.page,
				pageSize : $scope.pageSize
		};

		$minis.callService("POST", "~/aa/application/searchApp", request)
		.then(function(response) {
			$scope.total = response.data.total;
			$scope.applications = response.data.contents;
		}, function(response) {

		});
	}
});

webappContent.controller("ApplicationFormCtrl", function($parent, $scope, $uibModalInstance, $minis) {

	// Initialize modal data.
	$scope.action = $parent.action;
	$scope.applications = $parent.applications;
	$scope.application = angular.copy($parent.application) || {};
	
	// Binding event.
	$scope.createApp = function() {
		$minis.callService("POST", "~/aa/application/createApp", $scope.application)
		.then(function(response) {
			$scope.applications.splice(0, 0, response.data);
			MessageBox.success("新增應用程式資料成功.");
			$uibModalInstance.close();

		}, function(response) {
			$minis.bindErrorMessages($scope, response);
			if (response.status === 409) {
				MessageBox.error("新增應用程式資料失敗, 應用程式資料重複.");
			}
		});
	};

	$scope.updateApp = function() {
		$minis.callService("POST", "~/aa/application/updateApp", $scope.application)
		.then(function(response) {
			if ($parent.application.id === $scope.application.id) {
				var index = $scope.applications.indexOf($parent.application);
				$scope.applications.splice(index, 1, $scope.application);
				MessageBox.success("修改應用程式資料成功.");
			} else {
				$scope.applications.splice(0, 0, response.data);
				MessageBox.success("新增應用程式資料成功.");
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
