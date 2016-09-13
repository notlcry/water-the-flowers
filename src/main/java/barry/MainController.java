package barry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    public ServerHandler serverHandler;

    @Autowired
    private ClientStatus clientStatus;

    @RequestMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "aaa");
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
        synchronized (clientStatus){
            try {
                clientStatus.wait(3 * 1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(clientStatus.getPiStatus().equals(Constant.UNKNOWN)){

        }
        return "greeting";
    }

}
