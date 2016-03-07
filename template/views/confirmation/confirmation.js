/*global window, angular, console, alert, $*/

angular.module('bourbon.confirmation', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/confirmation', {
      templateUrl: 'views/confirmation/confirmation.html',
      controller: 'confirmationCtrl'
    });
  }])

  .controller('confirmationCtrl', function ($scope, $http, $uibModal) {
  
    $scope.bottles = [{
      id: 1,
      qty: 1,
      name: "Booker's Single Barrel Bourbon",
      price: 41.99,
      shortname: "bookers"
    }, {
      id: 2,
      qty: 1,
      name: "Blanton's Original Single Barrel Bourbon Whiskey",
      price: 49.99,
      shortname: "blantons"
    }, {
      id: 3,
      qty: 1,
      name: "Eagle Rare 10 Year Kentucky Straight Bourbon Whiskey",
      price: 39.99,
      shortname: "eagle-rare-10yr"
    }];
  
    $scope.$watch('bottles', function() {
      var total = 0
      for (var i = 0; i < $scope.bottles.length; i++) {
        total +=  $scope.bottles[i].price * $scope.bottles[i].qty;
      }
      $scope.total = total;
    },true);
  })


