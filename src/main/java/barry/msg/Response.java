package barry.msg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by huiyu on 16/9/13.
 */

@JsonIgnoreProperties
public class Response {
    private String type;

    private String result;

    private String info;

    private String status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
