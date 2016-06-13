
var webappContent = angular.module('minis.webapp.content', 
		[ 'ngAnimate', 'ui.bootstrap', 'minis.directives' ]);

webappContent.controller('ControlitemCtrl', function($scope, $uibModal, $minis, $log) {

	$scope.page = 1;
	$scope.pageSize = 20;
	$scope.maxPageSize = 10;

	display();

	$scope.pageChanged = function() {
		display();
	};

	$scope.createCtrlitm = function() {
		var modalInstance = $uibModal.open({
			templateUrl : 'ControlitemCreateForm.html',
			controller : 'ControlitemFormCtrl',
			size: 'lg',
			resolve : {
				$parent : function() {
					return {
						action : 'create',
						controlitems  : $scope.controlitems
					};
				}
			}
		});
	};

	$scope.updateCtrlitm = function(controlitem) {
		var modalInstance = $uibModal.open({
			templateUrl : 'ControlitemUpdateForm.html',
			controller : 'ControlitemFormCtrl',
			size: 'lg',
			resolve : {
				$parent : function() {
					return {
						action : 'update',
						controlitems  : $scope.controlitems,
						controlitem   : controlitem
					};
				}
			}
		});
	};

	$scope.deleteCtrlitm = function(controlitem) {
		$minis.callService("POST", "~/aa/controlitem/deleteControlitem", controlitem)
		.then(function(response) {
			var index = $scope.controlitems.indexOf(controlitem);
			$scope.controlitems.splice(index, 1);
			MessageBox.success("刪除控制項目資料成功.");

		}, function(response) {
			MessageBox.success("刪除控制項目資料失敗.");
		});
	};

	function display() {

		var request = {
				page     : $scope.page,
				pageSize : $scope.pageSize
		};

		$minis.callService("POST", "~/aa/controlitem/searchControlitems", request)
		.then(function(response) {
			$scope.total = response.data.total;
			$scope.controlitems = response.data.contents;
		}, function(response) {

		});
	}
});

webappContent.controller("ControlitemFormCtrl", function($parent, $scope, $uibModalInstance, $minis) {

	// Initialize modal data.
	$scope.action = $parent.action;
	$scope.controlitems = $parent.controlitems;
	$scope.controlitem = angular.copy($parent.controlitem) || {};
	
	// Binding event.
	$scope.createCtrlitm = function() {
		$minis.callService("POST", "~/aa/controlitem/createControlitem", $scope.controlitem)
		.then(function(response) {
			$scope.controlitems.splice(0, 0, response.data);
			MessageBox.success("新增控制項目資料成功.");
			$uibModalInstance.close();

		}, function(response) {
			$minis.bindErrorMessages($scope, response);
			if (response.status === 409) {
				MessageBox.error("新增控制項目資料失敗, 控制項目資料重複.");
			}
		});
	};

	$scope.updateCtrlitm = function() {
		$minis.callService("POST", "~/aa/controlitem/updateControlitem", $scope.controlitem)
		.then(function(response) {
			if ($parent.controlitem.id === $scope.controlitem.id) {
				var index = $scope.controlitems.indexOf($parent.controlitem);
				$scope.controlitems.splice(index, 1, $scope.controlitem);
				MessageBox.success("修改控制項目資料成功.");
			} else {
				$scope.controlitems.splice(0, 0, response.data);
				MessageBox.success("新增控制項目資料成功.");
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
