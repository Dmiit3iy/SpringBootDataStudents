package org.dmiit3iy.service;

import org.dmiit3iy.model.Car;
import org.dmiit3iy.model.Student;
import org.dmiit3iy.repository.CarRepository;
import org.dmiit3iy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;
    private StudentRepository studentRepository;

    @Autowired
    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(long studentId, Car car) {
        Student student = studentRepository.getById(studentId);
        car.setStudent(student);
        try {
            this.carRepository.save(car);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Car has already added!");
        }
    }

    @Override
    public List<Car> get() {
        return this.carRepository.findAll();
    }

    @Override
    public Car get(long id) {
        return this.carRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Car does not exists!"));
    }

    @Override
    public Car update(Car car) {
        Car base = this.get(car.getId());
        base.setBrand(car.getBrand());
        base.setPower(car.getPower());
        base.setYear(car.getYear());

        try {
            this.carRepository.save(base);
            return car;
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("TV is already exists!");
        }
    }

    @Override
    public Car delete(long id) {
        Car car = this.get(id);
        carRepository.deleteById(id);
        return car;
    }
}
