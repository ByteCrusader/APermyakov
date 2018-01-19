package ru.apermyakov.service;

import ru.apermyakov.domain.ad.Advert;
import ru.apermyakov.domain.car.Car;

import java.util.List;

public interface DAOInterface {

    Advert create(Car car, Advert advert, int userId);

    List<Advert> findAll();
}
