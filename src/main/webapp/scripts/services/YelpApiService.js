'use strict';

plantsmileseatsApp.factory('YelpApiService', function ($http) {
    return {
        searchByLocation: function(location, category) {
            var promise = $http.get('/app/rest/yelp/food/' + category + '/' + location).then(function(response){
                return response.data;
            });
            return promise;
        },
        serachByLatLong: function(lat, long, term, category) {
            var promise = $http.get('/app/rest/yelp', {params: {term: term, category: category, lat: lat,
                lon: long}}).then(function(response){
                return response.data;
            });
            return promise;
        }
    };
});
