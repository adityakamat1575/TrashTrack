package com.kairos.trashtrack;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Initialize the WebView
        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient()); // Ensure links open in the WebView

        // Enable JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Load the HTML snippet
        String html = "<div class=\"mapouter\"><div class=\"gmap_canvas\"><iframe class=\"gmap_iframe\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"https://maps.google.com/maps?width=600&amp;height=400&amp;hl=en&amp;q=2880%20Broadway,%20New%20York&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed\"></iframe><a href=\"https://embed-googlemap.com\">embed google map</a></div><style>.mapouter{position:relative;text-align:right;width:600px;height:400px;}.gmap_canvas {overflow:hidden;background:none!important;width:600px;height:400px;}.gmap_iframe {width:600px!important;height:400px!important;}</style></div>";
        webView.loadData(html, "text/html", "UTF-8");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up the WebView
        if (webView != null) {
            webView.destroy();
        }


//        // Initialize Google Maps Fragment
//        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Example location (latitude, longitude) - use the current location or a fixed location
//        LatLng currentLocation = new LatLng(37.7749, -122.4194); // San Francisco, CA
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 12));
//
//        // Call Foursquare API to get nearby places
//        getNearbyPlaces(currentLocation.latitude, currentLocation.longitude);
//    }
//
//    private void getNearbyPlaces(double latitude, double longitude) {
//        // Format the latitude and longitude for the API query
//        String latLng = latitude + "," + longitude;
//
//        // Create a Retrofit instance and make the API call
//        FoursquareApiService service = RetrofitClient.getRetrofitInstance().create(FoursquareApiService.class);
//        Call<FoursquareResponse> call = service.getNearbyPlaces(latLng, "recycling center", 1000, 10, FOURSQUARE_API_KEY);
//
//        call.enqueue(new Callback<FoursquareResponse>() {
//            @Override
//            public void onResponse(Call<FoursquareResponse> call, Response<FoursquareResponse> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    FoursquareResponse foursquareResponse = response.body();
//                    if (foursquareResponse.results != null) {
//                        for (FoursquareResponse.Venue venue : foursquareResponse.results) {
//                            // Add markers on the map for each venue
//                            LatLng venueLatLng = new LatLng(venue.location.lat, venue.location.lng);
//                            mMap.addMarker(new MarkerOptions()
//                                    .position(venueLatLng)
//                                    .title(venue.name));
//                        }
//                    }
//                } else {
//                    Toast.makeText(MapActivity.this, "No nearby places found.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<FoursquareResponse> call, Throwable t) {
//                Toast.makeText(MapActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
