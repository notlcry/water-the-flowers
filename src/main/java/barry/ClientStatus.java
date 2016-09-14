package barry;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by huiyu on 16/9/13.
 */

@Component
public class ClientStatus {
    private String piStatus;

    private String gpioStatus;

    private String info;

    @PostConstruct
    public void init(){
        this.piStatus = Constant.UNKNOWN;
        this.gpioStatus = Constant.UNKNOWN;
    }

    public void reset(){
        this.piStatus = Constant.UNKNOWN;
        this.gpioStatus = Constant.UNKNOWN;
        this.info = "";
    }

    public String getPiStatus() {
        return piStatus;
    }

    public void setPiStatus(String piStatus) {
        this.piStatus = piStatus;
    }

    public String getGpioStatus() {
        return gpioStatus;
    }

    public void setGpioStatus(String gpioStatus) {
        this.gpioStatus = gpioStatus;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
