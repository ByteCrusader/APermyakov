package ru.apermyakov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.apermyakov.domain.ad.Advert;
import ru.apermyakov.domain.car.Car;
import ru.apermyakov.domain.user.User;
import ru.apermyakov.repository.AdvertsRepository;
import ru.apermyakov.repository.CarRepository;

import java.util.List;

@Service
public class AdvertsDAO implements DAOInterface {

    private final AdvertsRepository advertsRepository;

    private final CarRepository carRepository;

    @Autowired
    public AdvertsDAO(AdvertsRepository advertsRepository, CarRepository carRepository) {
        this.advertsRepository = advertsRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Advert create(Car car, Advert advert, int userId) {
        Car addedCar = this.carRepository.save(car);
        advert.setCar(addedCar);
        advert.setUser(new User(userId));
        return this.advertsRepository.save(advert);
    }

    @Override
    public List<Advert> findAll() {
        return (List<Advert>) this.advertsRepository.findAll();
    }
}
