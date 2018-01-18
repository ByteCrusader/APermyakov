package ru.apermyakov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.apermyakov.model.ad.Advert;
import ru.apermyakov.model.car.Car;
import ru.apermyakov.persistant.DAOInterface;

/**
 * Front controller.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Controller
public class AdvertsController {

    @Autowired
    private DAOInterface dao;

    @GetMapping("/ads")
    public String showItems(ModelMap model) {
        model.addAttribute("adverts", this.dao.findAll());
        return "ads";
    }

    @PostMapping("/ads")
    public String addItems(@ModelAttribute Car car, @ModelAttribute Advert advert, @RequestParam int userId) {
        this.dao.create(car, advert, userId);
        return "redirect:ads.do";
    }
}
