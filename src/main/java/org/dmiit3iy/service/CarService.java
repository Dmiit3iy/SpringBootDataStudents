package org.dmiit3iy.service;

import org.dmiit3iy.model.Car;


import java.util.List;

public interface CarService {
    void add(long studentId, Car car);
    List<Car> get();
    Car get(long id);
    Car update(Car car);
    Car delete(long id);
}
