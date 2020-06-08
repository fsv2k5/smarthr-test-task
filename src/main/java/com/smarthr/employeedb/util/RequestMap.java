package com.smarthr.employeedb.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpMethod;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class RequestMap {

    public Pair<String, HttpMethod> getRandomUrl() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    private List<Pair<String, HttpMethod>> list = Stream.of(
//            Pair.of("https://reqres.in/api/users?page=2", HttpMethod.GET),
//            Pair.of("https://reqres.in/api/users/2", HttpMethod.GET),
//            Pair.of("https://reqres.in/api/users/23", HttpMethod.GET),
//            Pair.of("https://reqres.in/api/unknown/2", HttpMethod.GET),
//            Pair.of("https://reqres.in/api/unknown/23", HttpMethod.GET),
//            Pair.of("https://reqres.in/api/users", HttpMethod.POST),
//            Pair.of("https://reqres.in/api/users/2", HttpMethod.PUT),
            Pair.of("https://reqres.in/api/users/2", HttpMethod.DELETE)
    ).collect(Collectors.toList());
}
