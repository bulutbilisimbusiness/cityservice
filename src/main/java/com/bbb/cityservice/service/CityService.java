package com.bbb.cityservice.service;

import com.bbb.cityservice.exception.CityAlreadyExistsException;
import com.bbb.cityservice.exception.CityNotFoundException;
import com.bbb.cityservice.model.City;
import com.bbb.cityservice.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getCities(String name) {
        if(name==null) {
            return cityRepository.findAll();
        } else {
            return cityRepository.findAllByName(name);
        }
    }

    public City createCity(City newCity) {
      Optional<City> cityByName= cityRepository.findByName(newCity.getName());
      if(cityByName.isPresent()){
          throw new CityAlreadyExistsException("City already exist with name: "+newCity.getName());
      }

        return cityRepository.save(newCity);
    }
    public void deleteCity(String id) {
       cityRepository.deleteById(id);
    }
    public  City getCityById(String id) {
        return cityRepository.findById(id).orElseThrow(()->  new CityNotFoundException("City not found with id: "+id));


    }

    public void updateCity(String id, City newCity) {
        City oldCity=getCityById(id);
        oldCity.setName(newCity.getName());
        cityRepository.save(oldCity);
    }
}
