package ru.apermyakov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.apermyakov.domain.ad.Advert;

public interface AdvertsRepository extends CrudRepository<Advert, Integer> {
}
