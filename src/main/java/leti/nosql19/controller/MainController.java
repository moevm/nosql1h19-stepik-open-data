package leti.nosql19.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import leti.nosql19.model.Course;
import leti.nosql19.service.EntityService;
import leti.nosql19.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


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
        //entityService.saveOrUpdate(DataUtil.getCourseFromJson("literature_course.json"));

        model.addAttribute("listOfCourses", entityService.getCoursesNames());
        return "index";
    }

    @GetMapping("/importFile")
    public String getUploadPage(Model model) {
        List<String> courses = entityService.getCoursesNames();
        courses.add("All");
        model.addAttribute("listOfCourses", courses);
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
            String pathName = "src/main/resources/" + file.getOriginalFilename();
            Path path = Paths.get(pathName);
            Files.write(path, bytes);

            //save data to mongo
            entityService.saveOrUpdate(DataUtil.getCourseFromJson(pathName));

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping(value = "/download", produces = "application/json")
    public ResponseEntity<InputStreamResource> download(@RequestParam String courseName)
            throws IOException {

        List<Course> courses = new ArrayList<>();

        if (courseName.equals("All")) {
            courses = entityService.findAll();
        }

        ObjectMapper mapper = new ObjectMapper();
        byte[] buf = mapper.writeValueAsBytes(courses.size() > 1 ? courses : entityService.findById(courseName));

        String filename = courseName.toLowerCase() + ".json";

        return ResponseEntity
                .ok()
                .contentLength(buf.length)
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .header("Content-Disposition", "attachment; filename=" + filename)
                .body(new InputStreamResource(new ByteArrayInputStream(buf)));
    }


    @GetMapping("/statistics")
    public String getCourseStat(Model model, @RequestParam String courseName) {
        model.addAttribute("courseName", courseName);

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
        return "uploadStatus";
    }
}
