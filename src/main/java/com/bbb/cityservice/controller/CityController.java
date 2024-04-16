package com.bbb.cityservice.controller;

import com.bbb.cityservice.exception.CityAlreadyExistsException;
import com.bbb.cityservice.exception.CityNotFoundException;
import com.bbb.cityservice.model.City;

import com.bbb.cityservice.service.CityService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

private final CityService cityService;
    @GetMapping
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name){

        return  new ResponseEntity<>(cityService.getCities(name), OK);

    }
    @GetMapping("/{id}")
    public  ResponseEntity<City> getCity(@PathVariable String id){

        return new ResponseEntity<>(getCityById(id), OK);

    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity){

        return  new ResponseEntity<>(cityService.createCity(newCity), CREATED);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Void> getCity(@PathVariable String id,@RequestBody City newCity){

        cityService.updateCity(id,newCity);
        return new ResponseEntity<>(OK);
    }


    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteCity(@PathVariable String id){
       cityService.deleteCity(id);
        return  new ResponseEntity<>(OK);
    }
    private City getCityById(String id){
        return cityService.getCityById(id);

    }
    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<String> handleCityNotFoundException(CityNotFoundException ex){
        return  new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }
    @ExceptionHandler(CityAlreadyExistsException.class)
    public ResponseEntity<String> handleCityAlreadyExistsException(CityAlreadyExistsException ex){
        return  new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }
}
