package com.springlearn.cityservice.service;

import com.springlearn.cityservice.model.City;
import com.springlearn.cityservice.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City createCity(City newCity) {
        return cityRepository.save(newCity);
    }

    public boolean deleteCity(String id) {
        if(cityRepository.existsById(id)){
            cityRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }

    public City getCityById(String id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City could not be found"));
    } 

    public void updateCity(String id, City newCity) {
        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        cityRepository.save(oldCity);
    }
}
