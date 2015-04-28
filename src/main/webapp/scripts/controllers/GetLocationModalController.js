'use strict';

plantsmileseatsApp.controller('GetLocationModalController', function ($scope, $modalInstance) {
    $scope.data={name:""};

    $scope.ok = function () {
        //alert('name was: ' + $scope.data.name);
        $modalInstance.close($scope.data.name);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});
