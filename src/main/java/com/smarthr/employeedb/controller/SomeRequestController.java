package com.smarthr.employeedb.controller;

import com.smarthr.employeedb.vo.ExtendedResponse;
import com.smarthr.employeedb.service.SomeRequestService;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("somerequest")
@Setter(onMethod = @__({@Inject}))
@FieldDefaults(level = AccessLevel.PROTECTED)
public class SomeRequestController {
    SomeRequestService someRequestService;

    @ResponseBody
    @GetMapping
    public ExtendedResponse someRequest() throws Exception {
        return someRequestService.doRequest();
    }

}