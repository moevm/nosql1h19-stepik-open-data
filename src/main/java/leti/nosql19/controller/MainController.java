package leti.nosql19.controller;

import leti.nosql19.model.Entity;
import leti.nosql19.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final EntityService entityService;

    @Autowired
    public MainController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/start")
   // @ResponseBody
    public String getStartPage(Model model){

        //entityService.add(new Entity("Ivan", "Ivanov"));

        model.addAttribute("entity", entityService.findByLastName("Ivanov").get(0));
        //model.addAttribute("d", "d");
        return "index";
    }
}
