package com.springlearn.cityservice.repository;

import com.springlearn.cityservice.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City, String> {
}
