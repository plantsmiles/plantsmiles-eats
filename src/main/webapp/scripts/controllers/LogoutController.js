'use strict';

plantsmileseatsApp.controller('LogoutController', function ($location, AuthenticationSharedService) {
    AuthenticationSharedService.logout();
});