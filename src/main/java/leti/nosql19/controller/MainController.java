package leti.nosql19.controller;

import com.google.gson.Gson;
import leti.nosql19.service.EntityService;
import leti.nosql19.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class MainController {

    private final EntityService entityService;

    @Autowired
    public MainController(EntityService entityService) {
        this.entityService = entityService;
    }

    @GetMapping("/start")
    public String getStartPage(Model model) {

        //add data
        //       entityService.saveOrUpdate(DataUtil.getCourseFromJson("/Users/sergeyzyl/IdeaProjects/stepik/src/main/resources/literature_course.json"));
        model.addAttribute("userName", "Sergey");
        model.addAttribute("courseName", "Programming");
        model.addAttribute("listOfCourses", entityService.getCoursesNames());
        return "index";
    }

    @GetMapping("/importFile")
    public String getUploadPage(Model model) {
        model.addAttribute("userName", "Sergey");
        model.addAttribute("courseName", "Programming");
        return "importFile";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            byte[] bytes = file.getBytes();
            //path to the dir "resources"
            String pathName = "resources";
            Path path = Paths.get(pathName + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/statistics")
    public String getCourseStat(Model model, @RequestParam String courseName) {
        model.addAttribute("courseName", courseName);
        model.addAttribute("userName", "Sergey");

        model.addAttribute("attempts", entityService.getAttempts(courseName));
        model.addAttribute("comments", entityService.getComments(courseName));
        model.addAttribute("modules", entityService.getModules(courseName));

        model.addAttribute("listOfCourses", entityService.getCoursesNames());
        model.addAttribute("listOfUsers", entityService.getUsersName(courseName));
        return "courseStatistics";
    }

    @GetMapping("/personalStatistics")
    public String getPersonalStat(Model model, @RequestParam(required = false) String userName, @RequestParam(required = false) String courseName) {
        model.addAttribute("userName", userName);
        model.addAttribute("courseName", courseName);

        model.addAttribute("step_comments", entityService.getUserStepComments(userName, courseName));
        model.addAttribute("step_attempts", entityService.getUserStepAttempts(userName, courseName));
        model.addAttribute("mod_comments", entityService.getUserModuleComments(userName, courseName));
        model.addAttribute("mod_attempts", entityService.getUserModuleAttempts(userName, courseName));


        model.addAttribute("modules", entityService.getModules(courseName));

        model.addAttribute("listOfCourses", entityService.getCoursesNames());
        model.addAttribute("listOfUsers", entityService.getUsersName(courseName));

        return "personal";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus(Model model) {
        model.addAttribute("userName", "Sergey");
        model.addAttribute("courseName", "Programming");
        return "uploadStatus";
    }

    @PostMapping("/courseProcess")
    public void loadCourseStat(Model model) {

    }
}
