var myApp = angular.module('myApp', [ ]);

myApp.controller('MyCtrl', function MyCtrl($scope) {
$scope.copy = function() {
$scope.dst = $scope.src;
}
});

myApp.controller('MyCtrl1', function MyCtrl1($scope,$rootScope) {
$scope.copy = function() {
$rootScope.dist = $scope.src;
}
});

myApp.controller('ListCtrl', function ListCtrl($scope) {
$scope.items = [];
$scope.add = function() {
$scope.items.push($scope.item);
}
});

myApp.controller('ListCtrl2', function ListCtrl2($scope) {
$scope.onEvent= function(s) { console.log(s);
}
});

myApp.controller('MyCtrl2', function MyCtrl2($scope) {
$scope.items= [
{img: "a.jpg", rimg: "ra.jpg" },
{img: "b.jpg", rimg: "rb.jpg" }
];
});

myApp.controller('ListCtrl3', function ListCtrl3($scope) {
$scope.items= [];
$scope.add = function() {
$scope.items.push($scope.item);
						}
}
);