'use strict';

plantsmileseatsApp.controller('MainController', ['$scope', '$log', 'GoogleMapService', 'YelpApiService', 'GoogleMapApi'.ns(), '$modal',
    function ($scope, $log, GoogleMapService, YelpApiService, GoogleMapApi, $modal) {
        $scope.loading = true;
        GoogleMapApi.then(function(maps) {
            maps.visualRefresh = true;
            $scope.loading = false;
        });
        
        function searchYelpGenerateMap(position) {
            $scope.businesses = [];
            var currentLatitude, currentLongitude;

            if (angular.isDefined(position.coords)) {
                currentLatitude = position.coords.latitude;
                currentLongitude = position.coords.longitude;
            }
            else if (angular.isDefined(position.B) && angular.isDefined(position.k)) {
                currentLatitude = position.B;
                currentLongitude = position.k;
            }
            else {
                alert('no way to do the things')
            }

            YelpApiService.serachByLatLong(currentLatitude, currentLongitude, 'food', 'vegan').then(function (data) {
                var businesses = data.businesses;
                var markers = [];
                var i = 0;

                var bounds = new google.maps.LatLngBounds();

                angular.forEach(businesses, function (value, key, index) {
                    var latitude, longitude;

                    //TODO: metric around if included lat long
                    if (angular.isDefined(value.location.coordinate)) {
                        latitude = value.location.coordinate.latitude;
                        longitude = value.location.coordinate.longitude;

                        console.log('Business include lat long' + value);
                    }
                    else {
                        console.log('No lat/long found for: ' + value);
                        var address = value.location.address[0] + ',' + value.location.city + ',' + value.location.postal_code;
                        var geocodedLatLong = GoogleMapService.addressToLatLong(address);

                        if (geocodedLatLong) {
                            latitude = geocodedLatLong.latitude;
                            longitude = geocodedLatLong.longitude;
                            console.log('Geocode successful! ' + address);
                        } else {
                            console.log('Failed to geocode: ' + address);
                        }
                    }

                    if (latitude) {
                        var marker = {
                            id: i++,
                            latitude: latitude,
                            longitude: longitude,
                            name: value.name,
                            phone: value.display_phone,
                            imageUrl: value.image_url,
                            snippetText: value.snippet_text,
                            rating: value.rating,
                            ratingUrl: value.rating_img_url,
                            address: value.location.address[0] + ' ' + value.location.city + ', ' + value.location.postal_code
                            //icon: 'images/leaves9.png'
                        };

                        marker.closeClick = function () {
                            marker.showWindow = false;
                            $scope.$apply();
                        };
                        marker.onClicked = function () {
                            $scope.onMarkerClicked(marker);
                        };

                        markers.push(marker);
                        bounds.extend(new google.maps.LatLng(latitude, longitude));
                    }
                });

                var styles = [{
                    "featureType": "landscape.man_made",
                    "elementType": "geometry.fill",
                    "stylers": [{"lightness": "-5"}]
                }, {
                    "featureType": "landscape.man_made",
                    "elementType": "labels.text.fill",
                    "stylers": [{"saturation": "21"}]
                }, {
                    "featureType": "landscape.natural",
                    "elementType": "geometry.fill",
                    "stylers": [{"saturation": "1"}, {"color": "#eae2d3"}, {"lightness": "20"}]
                }, {
                    "featureType": "road.highway",
                    "elementType": "labels.icon",
                    "stylers": [{"saturation": "39"}, {"lightness": "7"}, {"gamma": "1.06"}, {"visibility": "on"}, {"hue": "#00b8ff"}, {"weight": "1.44"}]
                }, {
                    "featureType": "road.arterial",
                    "elementType": "geometry.stroke",
                    "stylers": [{"visibility": "on"}, {"lightness": "100"}, {"weight": "1.16"}, {"color": "#e0e0e0"}]
                }, {
                    "featureType": "road.arterial",
                    "elementType": "labels.icon",
                    "stylers": [{"saturation": "-16"}, {"lightness": "28"}, {"gamma": "0.87"}]
                }, {
                    "featureType": "water",
                    "elementType": "geometry.fill",
                    "stylers": [{"visibility": "on"}, {"saturation": "-75"}, {"lightness": "-15"}, {"gamma": "1.35"}, {"weight": "1.45"}, {"hue": "#00dcff"}]
                }, {
                    "featureType": "water",
                    "elementType": "labels.text.fill",
                    "stylers": [{"color": "#626262"}]
                }, {
                    "featureType": "water",
                    "elementType": "labels.text.stroke",
                    "stylers": [{"saturation": "19"}, {"weight": "1.84"}]
                }];

                $scope.map = {
                    center: {
                        latitude: currentLatitude,
                        longitude: currentLongitude
                    },
                    zoom: 13,
                    options: {
                        styles: styles,
                        draggable: true,
                        disableDefaultUI: true,
                        panControl: true,
                        navigationControl: true,
                        scrollwheel: true,
                        scaleControl: true
                    },
                    bounds: {
                        northeast: {
                            latitude: bounds.getNorthEast().lat(),
                            longitude: bounds.getNorthEast().lng()
                        },
                        southwest: {
                            latitude: bounds.getSouthWest().lat(),
                            longitude: bounds.getSouthWest().lng()
                        }
                    }
                };

                $scope.$watch(function () {
                    return $scope.map.bounds;
                }, function (nv, ov) {
                    $scope.businesses = markers;
                    i = 0;
                }, true);

                var markerToClose = null;
                $scope.onMarkerClicked = function (marker) {
                    if (markerToClose) {
                        markerToClose.showWindow = false;
                    }

                    markerToClose = marker; // for next go around
                    marker.showWindow = true;
                    $scope.$apply();
                    //window.alert("Marker: lat: " + marker.latitude + ", lon: " + marker.longitude + " clicked!!")
                };

                $scope.loading = false;

            });
        }

        function locateAndBeginSearch() {
            var geolocationAvailable = navigator.geolocation ? true : false;

            //var geolocationAvailable = false;

            if (geolocationAvailable) {
                GoogleMapService.currentPosition().then(function setCurrentPosition(position) {
                    searchYelpGenerateMap(position, location);
                });
            }
            else {
                var open = function () {
                    var modalInstance = $modal.open({
                        templateUrl: 'getLocation.html',
                        controller: 'GetLocationModalController'
                    });

                    modalInstance.result.then(function (location) {
                        GoogleMapService.addressToLatLong(location).then(function setCurrentPosition(position) {
                            searchYelpGenerateMap(position);
                        });
                    });
                };

                open();
            }
        }

        locateAndBeginSearch();
    }]);
