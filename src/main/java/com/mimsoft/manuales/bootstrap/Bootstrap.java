package com.mimsoft.manuales.bootstrap;

import com.mimsoft.manuales.application.controllers.api.ImageController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class Bootstrap extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addApiResourceClasses(resources);
        addWebResourceClasses(resources);
        return resources;
    }

    private void addApiResourceClasses(Set<Class<?>> resources) {
        resources.add(ImageController.class);
    }

    private void addWebResourceClasses(Set<Class<?>> resources) {
    }
}