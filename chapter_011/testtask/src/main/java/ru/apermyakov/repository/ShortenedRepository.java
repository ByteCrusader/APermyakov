package ru.apermyakov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.apermyakov.domain.shortened.Shortened;

public interface ShortenedRepository extends JpaRepository<Shortened, Integer> {

    Shortened findByLongUrl(String longUrl);
}
