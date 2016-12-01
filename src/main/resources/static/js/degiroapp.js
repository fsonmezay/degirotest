//js/degiroapp.js

(function() {
    var app = angular.module('degiroapp', ['ngRoute']);

    app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.
        when('/home', {
                templateUrl: 'partials/main.html',
                controller: 'MainController'
            })
            .otherwise({
                redirectTo: '/home'
            });
    }]);


    app.controller('MainController', ['$http', '$scope',  function($http, $scope) {
    	$scope.inputString="";
    	$scope.response="";
    	$scope.currentStyle="has-warning";
    	$scope.hasError=false;
    	
    	
    	$scope.sendInput = function(input) {
    		return $http.post("/input/send", {'input': input});
    	};
    	
    	function getResponse(input){
    		$scope.sendInput(input).then(function(response) {
                $scope.response = response.data;
            });
    	}
    	
    	$scope.submit = function(input) {
    		if (input.charAt(input.length -1) != "0") {
    			$scope.hasError=true;
    		}
    		else {
    			$scope.hasError=false;
    			getResponse(input);
    		}
	    	
	    };
    }]);

    
})();
