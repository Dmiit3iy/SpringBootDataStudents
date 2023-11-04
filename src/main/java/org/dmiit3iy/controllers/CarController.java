package org.dmiit3iy.controllers;

import org.dmiit3iy.dto.ResponseResult;
import org.dmiit3iy.model.Car;
import org.dmiit3iy.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")

public class CarController {
    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }
    @PostMapping(path = "/{studentId}")
    public ResponseEntity<ResponseResult<Car>> add(@PathVariable long studentId, @RequestBody Car car) {
        try {
            this.carService.add(studentId, car);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Car>>> get() {
        return new ResponseEntity<>(new ResponseResult<>(null,
                this.carService.get()), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Car>> get(@PathVariable long id) {
        try {
            Car car = this.carService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, car), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Car>> update(@RequestBody Car car) {
        try {
            Car res = this.carService.update(car);
            return new ResponseEntity<>(new ResponseResult<>(null, res), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Car>> delete(@PathVariable long id) {
        try {
            return new ResponseEntity<>(
                    new ResponseResult<>(null, this.carService.delete(id)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
    
}
