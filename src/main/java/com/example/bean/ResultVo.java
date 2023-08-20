package com.example.bean;

import lombok.Data;

@Data
public class ResultVo<T> {
    private Boolean success = Boolean.TRUE;
    private T data;

    private ResultVo() {}

    public static <T> ResultVo<T> buildSuccess(T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setData(data);
        return resultVo;
    }
}
