package ru.apermyakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.apermyakov.model.ad.Advert;

public interface AdvertsRepository extends CrudRepository<Advert, Integer> {
}
