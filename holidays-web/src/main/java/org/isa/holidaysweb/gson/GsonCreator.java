package org.isa.holidaysweb.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonCreator {
    public static Gson getGson () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson result = gsonBuilder
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                .create();
        return result;
    }
}
