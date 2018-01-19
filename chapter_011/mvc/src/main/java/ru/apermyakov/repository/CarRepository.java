package ru.apermyakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.apermyakov.domain.car.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
