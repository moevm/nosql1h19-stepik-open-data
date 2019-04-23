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
   //     entityService.saveOrUpdate(DataUtil.getCourseFromJson("C:\\Users\\Admin\\Desktop\\3 kurs\\nosql\\nosql1h19-stepik-open-data\\src\\main\\resources\\testcourse_course.json"));

        model.addAttribute("Jattempts", entityService.getAttempts("TestCourse"));
        model.addAttribute("Jcomments", entityService.getComments("TestCourse"));
        model.addAttribute("Jmodules", entityService.getModules("TestCourse"));

        model.addAttribute("python_attempts", entityService.getAttempts("Programming on Python"));
        model.addAttribute("python_comments", entityService.getComments("Programming on Python"));
        model.addAttribute("python_modules", entityService.getModules("Programming on Python"));

        return "index";
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
            String pathName = "/Users/sergeyzyl/IdeaProjects/stepik/src/main/resources/";
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
