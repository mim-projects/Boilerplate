package com.mimsoft.manuales.application.routes;

public class Route {
    private String name;
    private String url;

    public Route(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Route{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
