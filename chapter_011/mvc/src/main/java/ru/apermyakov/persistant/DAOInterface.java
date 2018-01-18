package ru.apermyakov.persistant;

import ru.apermyakov.model.ad.Advert;
import ru.apermyakov.model.car.Car;

import java.util.List;

public interface DAOInterface {

    Advert create(Car car, Advert advert, int userId);

    List<Advert> findAll();
}
