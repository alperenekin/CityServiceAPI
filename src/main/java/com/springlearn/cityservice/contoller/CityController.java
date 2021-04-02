package com.springlearn.cityservice.contoller;

import com.springlearn.cityservice.model.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {


    private final List<City> cities = new ArrayList<>();

    public CityController(){
        if(cities.isEmpty()){
            City city1 = new City(new Date(),"06","Ankara");
            City city2 = new City(new Date(), "34","Istanbul");
            cities.add(city1);
            cities.add(city2);
        }

    }
    
    @GetMapping
    public ResponseEntity<List<City>> getCities(){
        return  new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id){ //both has "id" keyword.
        City resultCity = getCityById(id);
        return  new ResponseEntity<City>(resultCity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City newCity){
        newCity.setCreateDate(new Date());
        cities.add(newCity);
        return new ResponseEntity<>(newCity,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putCity(@PathVariable String id, @RequestBody City newCity){
        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        oldCity.setCreateDate(new Date());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable String id){
        City city = getCityById(id);
        cities.remove(city);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private City getCityById(String id) {
        return cities.stream()  //for each le de yapılabilirdi.
                .filter(city -> city.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("City could not be found"));
    }
}
