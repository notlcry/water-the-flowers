package barry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

    @RequestMapping("/test")
    public String test() {
        return "index";
    }


    @RequestMapping("/start")
    public String start() {
        return "watering";
    }

    @RequestMapping("/stop")
    public String stop() {
        return "greeting";
    }

    @RequestMapping("/check")
    public String check() {
        return "greeting";
    }

}
