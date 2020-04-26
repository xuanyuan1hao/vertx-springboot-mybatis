package com.ayit.demo.entity;

import io.vertx.core.json.JsonObject;

public class BookConverter {
    public static void fromJson(JsonObject json, Book obj) {

    if (json.getValue("id") instanceof Number) {
        obj.setId(((Number)json.getValue("id")).longValue());
    }
    if (json.getValue("name") instanceof String) {
        obj.setName((String)json.getValue("name"));
    }
}

    public static void toJson(Book obj, JsonObject json) {

        if (obj.getId() != null) {
            json.put("id", obj.getId());
        }
        if (obj.getName() != null) {
            json.put("name", obj.getName());
        }
    }
}