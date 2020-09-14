var myApp = angular.module("myApp",[]);

myApp.controller('ListCtrl', function ListCtrl($scope) {
$scope.items = [];
$scope.add = function() {
$scope.items.push($scope.item);
$scope.item="";
}
});

myApp.controller('myCtrl1', function($scope, $filter) {
	var toUppercase = $filter('uppercase');

	$scope.toUppercasee = function(){
		$scope.input2 = toUppercase($scope.input1);
	} 
});

myApp.controller('myCtrl2', function($scope, $filter) {
	var toUppercase = $filter('uppercase');
	var toLowercase = $filter('lowercase');

	$scope.toUppercasee = function(){
		$scope.input2 = toUppercase($scope.input2);
	} 

	$scope.toLowercasee = function(){
		$scope.input2 = toLowercase($scope.input2);
	} 
});

myApp.controller('date1', function($scope) {

	
	$scope.dateAjrd = function(){
		$scope.date=new Date();
	} 
});

myApp.controller('limiToCtrl', function($scope) {
      $scope.numbers = [1,2,3,4,5,6,7,8,9];
      $scope.letters = "abcde";
      $scope.longNumber = 21031996;
  
    });

myApp.controller('MyCtrl3', function MyCtrl6($scope) {
    $scope.lowercaseLast = function (elem) {
        return elem.name === elem.name.toLowerCase()
    };
    var jb = { name: 'jb', gender: 'male' };
    var cyril = { name: 'Cyril', gender: 'male' };
    var agnes = { name: 'Agnes', gender: 'female' };
    var cedric = { name: 'cedric', gender: 'male' };
    $scope.ninjas = [jb, cyril, agnes, cedric];
});