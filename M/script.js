var myApp = angular.module('sampleApp',['ngRoute']);

myApp.config(['$routeProvider', function($routeProvider){
	$routeProvider
	.when('/',{
		controller: 'createController',
		templateUrl:'pages/create.html'})
		
	.when('/create',{
		controller: 'createController',
		templateUrl:'pages/create.html'})
		
	.when('/list',{
		controller: 'listController',
		templateUrl:'pages/list.html'})
	.otherwise({redirectTo:'/'});
}]);

myApp.controller('createController', ['$scope', function($scope){
	
	$scope.saveEntry = function(){
		//handle the attributes to save here
		$scope.createdAt = new Date();
	}
}]);

myApp.controller('listController', ['$scope', function($scope){
	$scope.message = 'list controller';
}]);