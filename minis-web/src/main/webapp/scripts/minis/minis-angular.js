
var minis_directives = angular.module(
		'minis.directives', []);

minis_directives.service("$minis", function($q, $http) {

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

});
