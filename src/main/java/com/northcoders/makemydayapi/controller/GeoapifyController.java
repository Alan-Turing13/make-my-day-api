package com.northcoders.makemydayapi.controller;

import com.northcoders.makemydayapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/geoapify/restaurants")
public class GeoapifyController {

    @Autowired
    RestaurantService restaurantService;

    @GetMapping()
    public ResponseEntity<String> getGeoapifyRestaurants() {
        restaurantService.getRestaurants();
        return new ResponseEntity<>("request made", HttpStatus.OK);
    }

}
