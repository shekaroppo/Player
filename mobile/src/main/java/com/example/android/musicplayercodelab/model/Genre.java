package com.example.android.musicplayercodelab.model;

import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("api_url")
    private String api_url;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    public String getApi_url() {
        return api_url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "ClassPojo [api_url = " + api_url + ", id = " + id + ", name = " + name + ", url = " + url + "]";
    }
}

