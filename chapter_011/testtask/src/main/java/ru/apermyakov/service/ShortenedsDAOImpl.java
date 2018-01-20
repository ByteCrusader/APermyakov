package ru.apermyakov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.apermyakov.domain.shortened.Shortened;
import ru.apermyakov.repository.ShortenedRepository;

import java.util.List;

@Service
public class ShortenedsDAOImpl implements DAO<Shortened> {

    private final ShortenedRepository shortenedRepository;

    @Autowired
    public ShortenedsDAOImpl(ShortenedRepository shortenedRepository) {
        this.shortenedRepository = shortenedRepository;
    }

    @Override
    @Transactional
    public Shortened create(Shortened shortened) {
        shortened.setShortUrl(
                String.format("http://short.com/%s"
                        , new Randomizer().buildRandomString(6)));
        shortened.setUses(0);
        return this.shortenedRepository.save(shortened);
    }

    @Override
    public List<Shortened> findAll() {
        return this.shortenedRepository.findAll();
    }

    public Shortened update(Shortened shortened) {
        return this.shortenedRepository.save(shortened);
    }

    @Transactional
    public Shortened findByLongUrl(String longUrl) {
        Shortened found = this.shortenedRepository.findByLongUrl(longUrl);
        int uses = found.getUses();
        found.setUses(++uses);
        return this.update(found);
    }
}
