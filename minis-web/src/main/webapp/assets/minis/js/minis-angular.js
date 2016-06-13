var minis_directives = angular.module('minis.directives', []);

///////////////////////////////////////////////////////////////////////////////
// Filter.

minis_directives.filter('abbreviate', function () {
    return function (value, max, tail) {
        if (!value) return '';
        if (!max) return value;
        if (value.length <= max) return value;
        value = value.substr(0, max);
        var lastspace = value.lastIndexOf(' ');
        if (lastspace != -1) {
            value = value.substr(0, lastspace);
        }
        return value + (tail || ' ...');
    };
});

///////////////////////////////////////////////////////////////////////////////
// Service.

minis_directives.service("$minis", function($q, $http) {

    /*******************************************************************
     * The Service.
     *******************************************************************/

	this.callService = function(method, url, data) {

		var deferred = $q.defer();

		Minis.CallService(method, url, data)
		.done(function(data, textStatus, jqXHR) {
			deferred.resolve({ 
				status : jqXHR.status, 
				statusText : jqXHR.statusText,
				data : data,
			});
		})
		.fail(function(jqXHR, textStatus, errorThrown) {
			deferred.reject({ 
				status : jqXHR.status, 
				statusText : jqXHR.statusText,
				message : jqXHR.responseText,
			});
		});

		return deferred.promise;
	};

	/*******************************************************************
	 * The Validation.
	 *******************************************************************/

	this.bindErrorMessages = function($scope, response) {
		this.clearErrorMessage($scope);
	    var statusCode = response.status;
	    if(statusCode === 400 || statusCode === 412){
	    	var messages = $.parseJSON(response.message);
	        var $validate = {};
	        $.each(messages.fieldErrors, function(index, value){
	            var errorMessages = value.errorMessage.join();
	            $validate[value.field] = {};
	            $validate[value.field].invalid = true; 
	            $validate[value.field].errorMessages = value.errorMessage.toString();
	            $scope.$validate = $validate;
	        });
	    }
	};

	this.clearErrorMessage = function($scope) {
		$scope.$validate = {};
	};

    /*******************************************************************
     * The UI.
     *******************************************************************/

   this.swapFrame = function($scope, a, b) {
       $scope[a] = true;
       $scope[b] = false;
    };

});
