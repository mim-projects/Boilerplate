package com.mimsoft.manuales.application.routes;

public enum Routes {
    HOME(new Route("home", "/index.xhtml")),
    ;
    private final Route route;

    Routes(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
