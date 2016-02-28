/*global window, angular, console, alert, $*/

angular.module('bourbon.store', ['ngRoute', 'ui.bootstrap'])

  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/store', {
      templateUrl: 'views/store/store.html',
      controller: 'storeCtrl'
    });
  }])

  .controller('storeCtrl', function ($scope, $http, $uibModal) {
  
    $http.get('../../bourbon.json').success(function (data){
      $scope.bottles = data;
    });
  
    // Modal
  
    $scope.open = function (size, bottle) {
      var modalInstance = $uibModal.open({
        animation: $scope.animationsEnabled,
        templateUrl: 'description',
        controller: 'modalInstanceCtrl',
        size: size,
        resolve: {
          bottle: function () {
            return bottle;
          }
        }
      })
    };
  
  
  })

.controller('modalInstanceCtrl', function($scope, $uibModalInstance, bottle) {
  
  $scope.bottle = bottle;
  
});
