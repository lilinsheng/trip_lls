package cn.wolfcode.trip.base.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JsonResult {
    private boolean success = true;
    private String msg;
    private Object result;

    public void mark(String msg){
        this.success = false;
        this.msg = msg;
    }
}
