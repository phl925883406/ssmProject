package com.imooc.myo2o.util.result;

public interface IResult {
    void setSuccess(Boolean var1);

    Boolean getSuccess();

    void setErrorCode(String var1);

    String getErrorCode();

    void setErrorMsg(String var1);

    String getErrorMsg();
}
