package ru.apermyakov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.apermyakov.model.ad.Ad;
import ru.apermyakov.model.car.Car;
import ru.apermyakov.persistant.AdsDAO;

/**
 * Front controller.
 *
 * @author apermyakov.
 * @version 1.0.
 * @since 17.01.2018.
 */
@Controller
public class AdsController {

    private final AdsDAO adsDAO = new AdsDAO();

    @GetMapping("/ads")
    public String showItems(ModelMap model) {
        model.addAttribute("ads", this.adsDAO.getAdsList());
        return "ads";
    }

    @PostMapping("/ads")
    public String addItems(@ModelAttribute Car car, @ModelAttribute Ad ad, @RequestParam int engineId, @RequestParam int gearboxId, @RequestParam int transmissionId, @RequestParam int userId) {
        this.adsDAO.addAd(userId, ad, car, engineId, gearboxId, transmissionId);
        return "redirect:ads.do";
    }
}
