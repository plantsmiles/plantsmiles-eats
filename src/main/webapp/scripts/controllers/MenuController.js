'use strict';

plantsmileseatsApp.controller('MenuController', function ($scope, $location) {
    $scope.isActive = function(route) {
        return route === $location.path();
    }
});
