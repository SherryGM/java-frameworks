/*global window, angular, console, alert, $*/

angular.module('bourbon.store', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/store', {
      templateUrl: 'views/store/store.html',
      controller: 'storeCtrl'
    });
  }])

  .controller('storeCtrl', function ($scope, $http) {
  
  $http.get('../../bourbon.json').success(function (data){
    $scope.bottles = data;
  });
  
  });
