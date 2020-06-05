package com.smarthr.employeedb.service;

import com.smarthr.employeedb.vo.ExtendedResponse;
import com.smarthr.employeedb.util.RequestMap;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SomeRequestService {

    @Autowired
    private RestTemplate restTemplate;

    public ExtendedResponse doRequest() throws Exception {
        Pair<String, HttpMethod> pair = RequestMap.getRandomUrl();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("X-Request-Source", "Desktop");
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                pair.getLeft(),
                pair.getRight(),
                request,
                String.class,
                1
        );
        return new ExtendedResponse(response.getBody());
    }
}
