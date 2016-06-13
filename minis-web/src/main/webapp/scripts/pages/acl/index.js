var myApp = angular.module('myApp', []);

//runs just fine
myApp.controller('InitCtrl', function($scope) {

    $scope.test = function() {
        AngularPlus.load(".content", "create.html");
    };

});

// We have to set up controllers ahead of time.
myApp.controller('AfterCtrl', function($scope) {

    $scope.create = function() {
        alert("load....");
    };

});
