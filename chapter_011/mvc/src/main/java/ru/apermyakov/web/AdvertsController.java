package ru.apermyakov.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.apermyakov.domain.ad.Advert;
import ru.apermyakov.domain.car.Car;
import ru.apermyakov.service.DAOInterface;

/**
 * Front controller.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Controller
public class AdvertsController {

    private final DAOInterface dao;

    @Autowired
    public AdvertsController(DAOInterface dao) {
        this.dao = dao;
    }

    @GetMapping("/adverts")
    public String showItems(ModelMap model) {
        model.addAttribute("adverts", this.dao.findAll());
        return "adverts";
    }

    @PostMapping("/adverts")
    public String addItems(@ModelAttribute Car car, @ModelAttribute Advert advert, @RequestParam int userId) {
        this.dao.create(car, advert, userId);
        return "redirect:adverts.do";
    }
}
