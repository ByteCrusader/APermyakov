package ru.apermyakov.persistant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.apermyakov.model.ad.Advert;
import ru.apermyakov.model.car.Car;
import ru.apermyakov.model.user.User;
import ru.apermyakov.repository.AdvertsRepository;
import ru.apermyakov.repository.CarRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdvertsDAO implements DAOInterface {

    @Resource
    private AdvertsRepository advertsRepository;

    @Resource
    private CarRepository carRepository;

    @Override
    @Transactional
    public Advert create(Car car, Advert advert, int userId) {
        Car addedCar = this.carRepository.save(car);
        advert.setCar(addedCar);
        advert.setUser(new User(userId));
        return this.advertsRepository.save(advert);
    }

    @Override
    @Transactional
    public List<Advert> findAll() {
        return (List<Advert>) this.advertsRepository.findAll();
    }
}
