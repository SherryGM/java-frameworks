/*global window, angular, console, alert, $*/

angular.module('bourbon.store', ['ngRoute'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/store', {
      templateUrl: 'views/store/store.html',
      controller: 'storeCtrl'
    });
  }])

  .controller('storeCtrl', function ($scope) {
  
  $scope.test = {
    hello: "hello WOrld!"
  };
  
  });
