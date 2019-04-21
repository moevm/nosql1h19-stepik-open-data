package leti.nosql19.controller;

import com.google.gson.Gson;
import leti.nosql19.service.EntityService;
import leti.nosql19.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    private final EntityService entityService;

    @Autowired
    public MainController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/start")
    public String getStartPage(Model model){

        entityService.saveOrUpdate(DataUtil.getCourseFromJson("/Users/sergeyzyl/IdeaProjects/stepik/src/main/resources/testcourse_course.json"));
        model.addAttribute("Jattempts", entityService.getAttempts("TestCourse"));
        model.addAttribute("Jcomments", entityService.getComments("TestCourse"));
        model.addAttribute("Jmodules", entityService.getModules("TestCourse"));
        return "index";
    }
}
