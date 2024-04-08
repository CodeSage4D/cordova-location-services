package cordova.plugin.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cordovaLocationPlugin extends CordovaPlugin {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationManager locationManager;
    private CallbackContext getCurrentLocationCallback;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getCurrentLocation".equals(action)) {
            getCurrentLocation(callbackContext);
            return true;
        } else if ("searchLocation".equals(action)) {
            String locationName = args.getString(0);
            searchLocation(locationName, callbackContext);
            return true;
        } else if ("toggleDayNightView".equals(action)) {
            toggleDayNightView(callbackContext);
            return true;
        } else if ("shareCurrentCoordinates".equals(action)) {
            shareCurrentCoordinates(callbackContext);
            return true;
        }
        return false;
    }

    private void getCurrentLocation(CallbackContext callbackContext) {
        if (cordova.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            getCurrentLocationCallback = callbackContext;
            startLocationUpdates();
        } else {
            cordova.requestPermission(this, REQUEST_LOCATION_PERMISSION, Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private void startLocationUpdates() {
        locationManager = (LocationManager) cordova.getActivity().getSystemService(cordova.getContext().LOCATION_SERVICE);
        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);
    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            JSONObject coordinates = new JSONObject();
            try {
                coordinates.put("latitude", latitude);
                coordinates.put("longitude", longitude);
                getCurrentLocationCallback.success(coordinates);
            } catch (JSONException e) {
                Log.e("LocationPlugin", "Error creating JSON object", e);
                getCurrentLocationCallback.error("Error creating JSON object");
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}

        @Override
        public void onProviderEnabled(String provider) {}

        @Override
        public void onProviderDisabled(String provider) {}
    };

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults) throws JSONException {
        super.onRequestPermissionResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                getCurrentLocationCallback.error("Location permission denied");
            }
        }
    }

    private void searchLocation(String locationName, CallbackContext callbackContext) {
        // Implement searchLocation logic here
        // This method should call appropriate JavaScript callbacks using callbackContext
        // You can use a library like Retrofit or Volley to make HTTP requests to the OpenStreetMap API.
        /* String url = "http://nominatim.openstreetmap.org/search?format=json&q=" + locationName;
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callbackContext.success(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callbackContext.error(error);
                    }
                });
        requestQueue.add(jsonRequest); */
    }

    private void toggleDayNightView(CallbackContext callbackContext) {
        // Implement toggleDayNightView logic here
        // This method should call appropriate JavaScript callbacks using callbackContext
        // You can toggle the day/night view using JavaScript by updating the CSS styles.
    }

    private void shareCurrentCoordinates(CallbackContext callbackContext) {
        // Implement shareCurrentCoordinates logic here
        // This method should call appropriate JavaScript callbacks using callbackContext
        // You can use the Web Share API if available, or fall back to showing an alert.
    }

    /* @Override
    public void onPause(boolean multitasking) {
        super.onPause(multitasking);
        stopLocationUpdates();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            stopLocationUpdates();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void stopLocationUpdates() {
        if (locationManager!= null) {
            locationManager.removeUpdates(locationListener);
        }
    } */

    // This method should call appropriate JavaScript callbacks using callbackContext
    // You can use the Web Share API if available, or fall back to showing an alert.
}
