package com.mimsoft.boilerplate.application.routes;

public enum Routes {
    ;
    private final Route route;

    Routes(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
