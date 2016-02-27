/*global window, angular, $*/
'use strict';

angular.module('bourbon', [
  'ngRoute',
  'bourbon.store'
])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/store'});
  }]);