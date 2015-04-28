'use strict';

plantsmileseatsApp.factory('VegGuideService', function ($http) {
    return {
        getByAddress: function(address){
            var promise = $http.get('http://www.vegguide.org/search/by-address/' + address).then(function(response) {
                return response.data;
            });
            return promise;
        },
        getByLatLong: function(lat, long){
            var promise = $http.get('http://www.vegguide.org/search/by-lat-long/' + lat + "," + long)
                .then(function(response) {
                return response.data;
            });
            return promise;
        }
    };
});