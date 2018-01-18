package ru.apermyakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.apermyakov.model.car.Car;

public interface CarRepository extends CrudRepository<Car, Integer> {
}
