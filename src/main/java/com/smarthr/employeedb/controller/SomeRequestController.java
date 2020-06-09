package com.smarthr.employeedb.controller;

import com.smarthr.employeedb.vo.ExtendedResponse;
import com.smarthr.employeedb.service.SomeRequestService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("somerequest")
@Setter(onMethod = @__({@Autowired}))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SomeRequestController {
    SomeRequestService someRequestService;

    @ResponseBody
    @GetMapping
    public ExtendedResponse someRequest() throws Exception {
        return someRequestService.doRandomRequest();
    }
}