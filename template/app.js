/*global window, angular, $*/
'use strict';

angular.module('bourbon', [
  'ngRoute',
  'ui.bootstrap',
  'ngAnimate',
  'bourbon.store'

])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/store'});
  }]);