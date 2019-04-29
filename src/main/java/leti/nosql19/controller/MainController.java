package leti.nosql19.controller;

import com.google.gson.Gson;
import leti.nosql19.service.EntityService;
import leti.nosql19.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String getStartPage(Model model){

        //add data
        entityService.saveOrUpdate(DataUtil.getCourseFromJson("C:\\Users\\Admin\\Desktop\\3 kurs\\nosql\\nosql1h19-stepik-open-data\\src\\main\\resources\\literature_course.json"));

        model.addAttribute("Jattempts", entityService.getAttempts("TestCourse"));
        model.addAttribute("Jcomments", entityService.getComments("TestCourse"));
        model.addAttribute("Jmodules", entityService.getModules("TestCourse"));

        model.addAttribute("python_attempts", entityService.getAttempts("Programming on Python"));
        model.addAttribute("python_comments", entityService.getComments("Programming on Python"));
        model.addAttribute("python_modules", entityService.getModules("Programming on Python"));

        model.addAttribute("literature_attempts", entityService.getAttempts("Literature"));
        model.addAttribute("literature_comments", entityService.getComments("Literature"));
        model.addAttribute("literature_modules",entityService.getModules("Literature"));

       model.addAttribute("listOfCourses",entityService.getCoursesNames());
        return "index";
    }

    @GetMapping("/personal")
    public String getPersonalPage(Model model){
        model.addAttribute("Test_mod_attempts",entityService.getUserModuleAttempts("Sergey Zyl","TestCourse"));
        model.addAttribute("Test_step_attempts",entityService.getUserStepAttempts("Sergey Zyl","TestCourse"));
        model.addAttribute("Test_mod_comments",entityService.getUserModuleComments("Sergey Zyl","TestCourse"));
        model.addAttribute("Test_step_comments",entityService.getUserStepComments("Sergey Zyl","TestCourse"));

        model.addAttribute("Python_step_comments",entityService.getUserStepComments("Sergey Zyl","Programming on Python"));
        model.addAttribute("Python_mod_comments",entityService.getUserModuleComments("Sergey Zyl","Programming on Python"));
        model.addAttribute("Python_step_attempts",entityService.getUserStepAttempts("Sergey Zyl","Programming on Python"));

        model.addAttribute("literature_mod_attempts",entityService.getUserModuleAttempts("Sergey Zyl","Literature"));
        model.addAttribute("literature_mod_comments",entityService.getUserModuleComments("Sergey Zyl","Literature"));

        model.addAttribute("Python_mod_attempts",entityService.getUserModuleAttempts("Sergey Zyl","Programming on Python"));
        model.addAttribute("Jmodules", entityService.getModules("TestCourse"));
        model.addAttribute("literature_modules",entityService.getModules("Literature"));
        model.addAttribute("python_modules", entityService.getModules("Programming on Python"));
        model.addAttribute("listOfCourses",entityService.getCoursesNames());
        return "personal";
    }

    @GetMapping("/importFile")
    public String getUploadPage(Model model){
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

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}
