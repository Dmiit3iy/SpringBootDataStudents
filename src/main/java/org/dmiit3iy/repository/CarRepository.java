package org.dmiit3iy.repository;

import org.dmiit3iy.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
