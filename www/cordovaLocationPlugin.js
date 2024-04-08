var exec = require('cordova/exec');

var CordovaLocationPlugin = {
    getCurrentLocation: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'CordovaLocationPlugin', 'getCurrentLocation', []);
    },
    searchLocation: function(locationName, successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'CordovaLocationPlugin', 'searchLocation', [locationName]);
    },
    toggleDayNightView: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'CordovaLocationPlugin', 'toggleDayNightView', []);
    },
    shareCurrentCoordinates: function(successCallback, errorCallback) {
        exec(successCallback, errorCallback, 'CordovaLocationPlugin', 'shareCurrentCoordinates', []);
    }
};

module.exports = CordovaLocationPlugin;
