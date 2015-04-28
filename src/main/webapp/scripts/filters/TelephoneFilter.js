'use strict';

plantsmileseatsApp.filter('tel', function () {
    return function (tel) {
        if (!tel) { return ''; }

        var value = tel.toString().trim().replace(/^\+/, '');

        var city, number;

        // +1PPP####### -> C (PPP) ###-####
        city = value.slice(2, 5);
        number = value.slice(6);

        number = number.slice(0, 3) + '-' + number.slice(3);

        return ("(" + city + ") " + number).trim();
    };
});
