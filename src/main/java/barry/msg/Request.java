package barry.msg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by huiyu on 16/9/13.
 */

@JsonIgnoreProperties
public class Request {
    private String type;

    private String info;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
