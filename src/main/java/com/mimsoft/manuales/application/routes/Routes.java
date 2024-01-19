package com.mimsoft.manuales.application.routes;

public enum Routes {
    HOME(new Route("home", "/index.xhtml")),
    ADMIN_LOGOUT(new Route("logout", null)),
    ADMIN_LOGIN(new Route("admin_login", "/admin/index.xhtml")),
    ADMIN_PROFILE(new Route("admin_profile", "/admin/profile.xhtml")),
    ADMIN_HOME(new Route("admin_home", "/admin/home.xhtml")),
    ADMIN_LANGUAGE(new Route("admin_language", "/admin/language.xhtml")),
    ADMIN_PROJECT(new Route("admin_project", "/admin/project.xhtml")),
    ADMIN_PROJECT_CONTENT(new Route("admin_project_content", "/admin/project_content.xhtml")),
    ADMIN_SECTION(new Route("admin_section", "/admin/section.xhtml")),
    ADMIN_SECTION_CONTENT(new Route("admin_section_content", "/admin/section_content.xhtml")),
    APP_PROJECT(new Route("app_project", "/app/project.xhtml")),
    APP_SECTION(new Route("app_section", "/app/section.xhtml")),
    ;
    private final Route route;

    Routes(Route route) {
        this.route = route;
    }

    public Route getRoute() {
        return route;
    }
}
