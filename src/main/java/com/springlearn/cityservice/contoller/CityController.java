package com.springlearn.cityservice.contoller;

import com.springlearn.cityservice.model.City;
import com.springlearn.cityservice.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities(){
        return  new ResponseEntity<>(cityService.getCities(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id){ //both has "id" keyword.
        return  new ResponseEntity<City>(getCityById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity){
        return new ResponseEntity<>(cityService.createCity(newCity),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putCity(@PathVariable String id, @RequestBody City newCity){
        cityService.updateCity(id,newCity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id){
        boolean result = cityService.deleteCity(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private City getCityById(String id) {
        return cityService.getCityById(id);
    }
}
