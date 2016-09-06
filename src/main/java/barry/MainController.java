package barry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    public ServerHandler serverHandler;

    @RequestMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }


    @RequestMapping("/start")
    public String start() {
        serverHandler.sendStart();
        return "watering";
    }

    @RequestMapping("/stop")
    public String stop() {
        serverHandler.sendStop();
        return "greeting";
    }

    @RequestMapping("/check")
    public String check() {
        serverHandler.sendCheck();
        return "greeting";
    }

}
