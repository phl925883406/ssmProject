package com.imooc.myo2o.util.result;

public enum DefaultErrorCode implements IErrorCode {
    SUCCESS							(200, "成功"),
    SYS_ERROR						(500, "系统异常"),
    PARAM_ERROR						(400, "参数错误"),
    NOT_ENOUGH_ROLE					(403, "权限不足");

    private Integer code;
    private String msg;


    DefaultErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code.toString();
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(Integer code){
        if(code == null){
            return null;
        }
        for (DefaultErrorCode a : DefaultErrorCode.values()){
            if(a.code.equals(code)){
                return a.getMsg();
            }
        }
        return code.toString();
    }
}
