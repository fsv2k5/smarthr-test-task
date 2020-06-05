package com.smarthr.employeedb.model;

import com.smarthr.employeedb.util.DateTimeUtil;

import java.io.Serializable;

public class ExtendedResponse implements Serializable {

    private final String timestamp;
    private final String responseBody;

    public ExtendedResponse(String responseBody) {
        this.responseBody = responseBody;
        timestamp = DateTimeUtil.getDeteTimeNowAsString();
    }

}
