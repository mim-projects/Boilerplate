package com.mimsoft.boilerplate.application.controllers.shared;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.util.Map;

@Named("requestCtrl")
@RequestScoped
public class RequestController {
    public String getParam(String key) {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        return params.get(key);
    }

    public String getContext() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public String getResourcesUrl() {
        String host = FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap().get("host");
        String protocol = host.contains("localhost:") ?"http://" :"https://";
        return protocol + host + getContext() + "/jakarta.faces.resources";
    }

    public String getResourcesForUrl(String name, String lib) {
        return getResourcesUrl() + "/" + name + ".xhtml?ln=" + lib;
    }
}