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

        //add data
        entityService.saveOrUpdate(DataUtil.getCourseFromJson("C:\\Users\\Admin\\Desktop\\3 kurs\\nosql\\nosql1h19-stepik-open-data\\src\\main\\resources\\testcourse_course.json"));

        model.addAttribute("Jattempts", entityService.getAttempts("TestCourse"));
        model.addAttribute("Jcomments", entityService.getComments("TestCourse"));
        model.addAttribute("Jmodules", entityService.getModules("TestCourse"));

        model.addAttribute("python_attempts", entityService.getAttempts("Programming on Python"));
        model.addAttribute("python_comments", entityService.getComments("Programming on Python"));
        model.addAttribute("python_modules", entityService.getModules("Programming on Python"));

        return "index";
    }
}
