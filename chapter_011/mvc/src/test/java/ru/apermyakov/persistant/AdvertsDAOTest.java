package ru.apermyakov.persistant;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.apermyakov.model.ad.Advert;
import ru.apermyakov.model.car.Car;

public class AdvertsDAOTest {

    @Test
    public void whenThan() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        AdvertsDAO adsDAO = context.getBean(AdvertsDAO.class);
        adsDAO.create(new Car(), new Advert(), 1);
    }
}