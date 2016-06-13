/**
 * Created by admin on 2013/12/3.
 */

var core = angular.module('core', [ '$strap.directives' ]);

core.value('$strapConfig', {
    datepicker : {
        format : 'yyyy/mm/dd',
        language : 'zh-TW'
    }
});

function ProjectController($scope) {

    $scope.create = function() {

        var project = {
            "code" : $scope.code,
            "name" : $scope.name,
            "startDate" : $scope.startDate,
            "closeDate" : $scope.closeDate,
            "kickoffDate" : $scope.kickoffDate
        };

        $.postJSON("create.json", project).done(function(data) {
            window.location.replace("./../index.html");
        }).fail(function(data) {
            alert("failure");
        });

    };

    $scope.cancel = function() {
        window.location.replace("./../index.html");
    };
    
}
