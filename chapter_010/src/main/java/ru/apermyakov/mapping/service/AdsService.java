package ru.apermyakov.mapping.service;

import ru.apermyakov.mapping.ad.Ad;
import ru.apermyakov.mapping.car.Car;
import ru.apermyakov.mapping.car.Engine;
import ru.apermyakov.mapping.car.Gearbox;
import ru.apermyakov.mapping.car.Transmission;
import ru.apermyakov.mapping.persistance.*;
import ru.apermyakov.mapping.user.User;

import java.util.List;

public class AdsService {

    private final AdsPersist persist = new AdsPersist();

    public void create(Car car, String engineId, String gearboxId, String transmissionId, String userName, Ad ad) {
        Engine engine = new EnginePersist().getById(engineId);
        Gearbox gearbox = new GearboxPersist().getById(gearboxId);
        Transmission transmission = new TransmissionPersist().getById(transmissionId);
        User user = new UserPersist().getByName(userName);
        car.setEngine(engine);
        car.setGearbox(gearbox);
        car.setTransmission(transmission);
        ad.setCar(car);
        ad.setUser(user);
        this.persist.create(ad);
    }

    public List<Ad> findAll() {
        return this.persist.getAll();
    }
}
