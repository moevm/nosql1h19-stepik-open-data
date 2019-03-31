package leti.nosql19.controller;

import leti.nosql19.model.*;
import leti.nosql19.repository.EntityRepository;
import leti.nosql19.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class MainController {

    private final EntityService entityService;

    private final EntityRepository entityRepository;

    @Autowired
    public MainController(EntityService entityService, EntityRepository entityRepository) {
        this.entityService = entityService;
        this.entityRepository = entityRepository;
    }

    @GetMapping("/start")
    public String getStartPage(Model model){
        //test db

        UserStep initUserStep = new UserStep(1, 20, 2, true);
        UserModule initUserModule = new UserModule("Test module2", 20, 2, true);
        UserCourse initUserCourse = new UserCourse(20, 2, 100.0, 1, true);

        ArrayList<UserModule> initUserModules = new ArrayList<>();
        initUserModules.add(initUserModule);

        ArrayList<UserStep> initUserSteps = new ArrayList<>();
        initUserSteps.add(initUserStep);

        UserProgress initUserProgress = new UserProgress(initUserCourse, initUserModules, initUserSteps);

        User initUser = new User("Jools Blinnikova", initUserProgress);
        ArrayList<User> initUsers = new ArrayList<>();
        initUsers.add(initUser);

        Step initStep = new Step(1, 20, 1, 2);
        ArrayList<Step> initSteps = new ArrayList<>();
        initSteps.add(initStep);

        Module initModule = new Module("Test module", 20, 1, 2, initSteps);
        ArrayList<Module> initModules = new ArrayList<>();
        initModules.add(initModule);

        CourseParts initCourseParts = new CourseParts(initModules, initUsers);

        CourseUsersDetails initCourseUsersDetails = new CourseUsersDetails(1, 1, 1);

        CourseInformation initCourseInformation = new CourseInformation(initCourseUsersDetails, 1, 1, 2);

        entityService.add(new Course("Test course", initCourseParts, initCourseInformation));
        System.out.println(entityRepository.findAll());;
        return "index";
    }
}
