package com.northcoders.makemydayapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class RestaurantService {

    @Value("${api.key}")
    private String API_KEY;

    // St Paul's Cathedral
    private final String LATITUDE = "51.5138438";
    private final String LONGITUDE = "-0.13955";

    // 10 kilometre radius
    private String radius = "10000";
    private String resultsLimit = "500";


    public String getRestaurants(){

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.SECONDS)
                .build();
        System.out.println("client built");

        String endpoint = "https://api.geoapify.com/v2/places?categories=catering&filter=circle:"
                +LONGITUDE+","+LATITUDE+","+radius+"&limit="+resultsLimit+"&apiKey="+API_KEY;
        System.out.println("endpoint:"+  endpoint);

        Request request = new Request.Builder()
                .url(endpoint)
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Content-Type", "application/json")
                .build();
        System.out.println("built request");

        ObjectMapper objectMapper = new ObjectMapper();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Got response: ");
            objectMapper.writeValue(new File("geoapify-restaurants.json"), response.body().string());
            System.out.println("Wrote json file");
        } catch (IOException e) {
            System.err.println("Error making http request " + e.getMessage());
        }
        return "restaurant service complete";
    }
}
