'use strict';

plantsmileseatsApp.factory('GoogleMapService', function ($http, $q) {
    return {
        currentPosition: function() {
            var deferred = $q.defer();
            var geolocationAvailable = navigator.geolocation ? true : false;
            if (geolocationAvailable) {
                navigator.geolocation.getCurrentPosition(
                    function(position) {
                        deferred.resolve(position);
                    },
                    function(error) {
                        deferred.resolve(error.message);
                    }
                );
            }
            else {
                deferred.reject('Geolocation is not supported in this browser');
            }

            return deferred.promise;
        },
        addressToLatLong: function(address) {
            var deferred = $q.defer();
            var geocoder = new google.maps.Geocoder();

            //TODO: handle multiple adresses, its setup just need to not pluck first result
            geocoder.geocode( { 'address': address}, function(results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    deferred.resolve(results[0].geometry.location);
                }
                else {
                    deferred.reject('Unable to geocode address: ' + address);
                }
            });

            return deferred.promise;
        }
    };
});
