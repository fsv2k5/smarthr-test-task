package com.smarthr.employeedb.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class Gsons {

    public static final Gson INSTANCE = configureBuilder(new GsonBuilder()).create();

    public static GsonBuilder configureBuilder(GsonBuilder builder) {
        return builder.setDateFormat("yyyy-MM-dd");
    }
}
