package com.kairos.trashtrack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoursquareApiService {
    // Foursquare API endpoint for searching venues
    @GET("v3/places/nearby")
    Call<FoursquareResponse> getNearbyPlaces(
            @Query("ll") String latitudeLongitude, // Format: latitude,longitude
            @Query("query") String query,          // Example: "recycling center"
            @Query("radius") int radius,           // Search radius in meters
            @Query("limit") int limit,             // Max number of results
            @Query("apiKey") String apiKey         // Your Foursquare API key
    ); // Added the missing semicolon here
}