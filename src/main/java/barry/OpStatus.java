package barry;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by huiyu on 16/9/13.
 */

@Component
public class OpStatus {
    private String result;

    private String info;

    @PostConstruct
    public void init(){
        this.result = Constant.UNKNOWN;
        this.info= "";
    }

    public void reset(){
        this.result = Constant.UNKNOWN;
        this.info= "";
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
