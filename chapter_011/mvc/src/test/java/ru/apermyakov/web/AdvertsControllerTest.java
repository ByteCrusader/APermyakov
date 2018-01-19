package ru.apermyakov.web;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.apermyakov.domain.ad.Advert;
import ru.apermyakov.domain.car.Car;
import ru.apermyakov.domain.user.User;
import ru.apermyakov.service.AdvertsDAO;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(AdvertsController.class)
public class AdvertsControllerTest {

    @Autowired
    private MockMvc mvcMock;

    @MockBean
    private AdvertsDAO service;

    @Test
    @WithMockUser(username = "Ivan", password = "ivan")
    public void whenThen() throws Exception {
        User user = new User(1);
        user.setName("Ivan");
        user.setEmail("ivan");
        user.setPassword("ivan");
        Car car = new Car(1);
        car.setMake("Toyota");
        car.setModel("Allion");
        car.setYear(2008);
        car.setEngine("petrol");
        car.setHorsepower(150);
        car.setGearbox("auto");
        Advert advert = new Advert(1);
        advert.setTitle("Sell Toyota Allion 2008");
        advert.setCar(car);
        advert.setUser(user);
        advert.setPhoto("photoPath");
        advert.setStatus("active");
        given(
                this.service.findAll()
        ).willReturn(
                new ArrayList<Advert>(
                        Lists.newArrayList(advert)
                )
        );
        this.mvcMock.perform(
                get("/adverts").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("adverts")
        );
    }

    @Test
    @WithMockUser(username = "Ivan", password = "ivan")
    public void when1Then() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("title", "Sell Toyota Allion 2008");
        params.add("status", "active");
        params.add("make", "Toyota");
        params.add("model", "Allion");
        params.add("year", "2008");
        params.add("engine", "petrol");
        params.add("horsepower", "150");
        params.add("gearbox", "auto");
        params.add("userId", "1");
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Allion");
        car.setYear(2008);
        car.setEngine("petrol");
        car.setHorsepower(150);
        car.setGearbox("auto");
        Advert advert = new Advert();
        advert.setTitle("Sell Toyota Allion 2008");
        advert.setStatus("active");
        this.mvcMock.perform(
                post("/adverts").params(params)
        ).andExpect(
                status().is3xxRedirection()
        );

        verify(this.service, times(1)).create(car, advert, 1);
    }
}