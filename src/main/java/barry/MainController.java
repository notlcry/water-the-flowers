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

    @Autowired
    private OpStatus opStatus;

    @RequestMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "aaa");
        return "greeting";
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/home")
    public String backHome() {
        return "index";
    }


    @RequestMapping("/start")
    public String start(Model model) {

        if (!serverHandler.hasRpi()){
            model.addAttribute("message", Constant.NO_RPI_MSG);
            return "fail";
        }

        opStatus.reset();
        serverHandler.sendStart();
        synchronized (opStatus){
            try {
                opStatus.wait(Constant.TIME_OUT * 1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(opStatus.getResult().equals(Constant.UNKNOWN)){
            model.addAttribute("message", Constant.TIME_OUT_MSG);
            return "fail";
        }else if (opStatus.getResult().equals(Constant.FAIL)){
            model.addAttribute("message", opStatus.getInfo());
            return "fail";
        }else if (opStatus.getResult().equals(Constant.SUCCESS)){
            model.addAttribute("message", "Great! The Rpi start watering the flowers.");
            return "watering";
        }
        return "greeting";
    }

    @RequestMapping("/stop")
    public String stop(Model model) {
        if (!serverHandler.hasRpi()){
            model.addAttribute("message", Constant.NO_RPI_MSG);
            return "fail";
        }

        opStatus.reset();
        serverHandler.sendStop();
        synchronized (opStatus){
            try {
                opStatus.wait(Constant.TIME_OUT * 1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(opStatus.getResult().equals(Constant.UNKNOWN)){
            model.addAttribute("message", Constant.TIME_OUT_MSG);
            return "fail";
        }else if (opStatus.getResult().equals(Constant.FAIL)){
            model.addAttribute("message", opStatus.getInfo());
            return "fail";
        }else if (opStatus.getResult().equals(Constant.SUCCESS)){
            model.addAttribute("message", "Great! The Rpi will have a rest.");
            return "stop";
        }

        return "greeting";
    }

    @RequestMapping("/check")
    public String check(Model model) {
        if (!serverHandler.hasRpi()){
            model.addAttribute("message", Constant.NO_RPI_MSG);
            return "fail";
        }

        clientStatus.reset();
        serverHandler.sendCheck();
        synchronized (clientStatus){
            try {
                clientStatus.wait(Constant.TIME_OUT * 1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(clientStatus.getGpioStatus().equals(Constant.UNKNOWN)){
            model.addAttribute("message", Constant.TIME_OUT_MSG);
            return "fail";
        }else if (clientStatus.getGpioStatus().equals(Constant.FAIL)){
            model.addAttribute("message", clientStatus.getInfo());
            return "fail";
        }else if (clientStatus.getGpioStatus().equals(Constant.WATER_ON)){
            model.addAttribute("message", "The Rpi is watering the flowers.");
            return "watering";
        }else if (clientStatus.getGpioStatus().equals(Constant.WATER_OFF)){
            model.addAttribute("message", "The Rpi is sleeping...");
            return "stop";
        }

        return "greeting";
    }

}
