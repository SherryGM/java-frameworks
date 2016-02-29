/*global window, angular, $*/
'use strict';

angular.module('bourbon', [
  'ngRoute',
  'ui.bootstrap',
  'ngAnimate',
  'bourbon.store',
  'bourbon.cart'

])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/store'});
  }]);