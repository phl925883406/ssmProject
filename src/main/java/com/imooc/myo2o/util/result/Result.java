package com.imooc.myo2o.util.result;

import java.io.Serializable;

/**
 * @author admin
 */
public class Result implements IResult, Serializable {
    private static final long serialVersionUID = -3204373332575547214L;
    private Boolean success = false;
    private String errorCode;
    private String errorMsg;
    private Object content;


    public void setErrorCode(IErrorCode code) {
        this.errorCode = code.getCode();
        this.errorMsg = code.getMsg();
        if (DefaultErrorCode.SUCCESS.getCode().equals(errorCode)) {
            this.success = true;
        } else {
            this.success = false;
        }
    }

    @Override
    public Boolean getSuccess() {
        return success;
    }

    @Override
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
