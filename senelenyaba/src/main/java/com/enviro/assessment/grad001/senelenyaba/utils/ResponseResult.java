package com.enviro.assessment.grad001.senelenyaba.utils;
// I created this class to customize my response
public class ResponseResult {
    private int statusCode;
    private String message;
    private Object results;

    public ResponseResult(int statusCode, String message, Object results){
        this.statusCode = statusCode;
        this.message = message;
        this.results = results;
    }


}
